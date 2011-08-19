package org.jboss.seam.examples.seamcrm.aaa;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.jboss.logging.Logger;
import org.jboss.seam.examples.seamcrm.user.User;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;

import com.google.common.base.Strings;

/**
 * 
 * @author Cody Lerum
 * 
 */
@SessionScoped
public class Login extends BaseAuthenticator implements Serializable, org.jboss.seam.security.Authenticator {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;
    
    @Inject
    private Credentials credentials;

    @Inject
    private Messages messages;

    @Inject
    private EntityManager em;

    @Inject
    @Authenticated
    private Event<User> loginEvent;

    @Inject
    private Event<EventLog> eventLog;

    @Inject
    private SecurityUtil securityUtil;

    public void authenticate() {

        if (!Strings.isNullOrEmpty(credentials.getUsername())) {
            log.info("Authenticating: \"" + credentials.getUsername() + "\"");

            try {
                User u = em.createQuery("from User u join fetch u.contact where u.contact.emailAddress=:username", User.class)
                        .setParameter("username", credentials.getUsername()).getSingleResult();

                if (u != null) {
                    PasswordCredential password = (PasswordCredential) credentials.getCredential();
                    String hashedPassword = securityUtil.hash(u, password.getValue());
                    if (hashedPassword.equals(u.getPassword())) {
                        loginEvent.fire(u);
                        u.setDateLastLogin(new Date());
                        setUser(new SimpleUser(u.getContact().getEmailAddress()));
                        setStatus(AuthenticationStatus.SUCCESS);
                        eventLog.fire(new EventLog(u, "User Logged In"));
                    } else {
                        messages.info("Incorrect Password");
                        setStatus(AuthenticationStatus.FAILURE);
                    }
                }

            } catch (NoResultException e) {
                messages.info("Invalid Username");
                setStatus(AuthenticationStatus.FAILURE);
            } catch (NonUniqueResultException e) {
                throw new RuntimeException("Unpossible! There can't be two users with the same username");
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("Error Hashing the password", e);
            }
        }
    }
}
