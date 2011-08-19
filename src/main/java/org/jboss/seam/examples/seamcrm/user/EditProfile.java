package org.jboss.seam.examples.seamcrm.user;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;
import org.jboss.seam.examples.seamcrm.aaa.Authenticated;
import org.jboss.seam.examples.seamcrm.aaa.SessionUser;
import org.jboss.seam.examples.seamcrm.contact.Contact;


/**
 * 
 * @author Cody Lerum
 * 
 */
@Named
public class EditProfile {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private SessionUser sessionUser;

    @Inject
    @Authenticated
    private Contact sessionContact;

    public String updateProfile() {
        sessionUser.getUser().setContact(sessionContact);
        em.flush();
        log.info("Profile updated");
        return "/admin/home.xhtml";
    }
}
