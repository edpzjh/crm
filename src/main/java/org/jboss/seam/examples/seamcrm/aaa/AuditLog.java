package org.jboss.seam.examples.seamcrm.aaa;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.examples.seamcrm.user.User;


/**
 * 
 * @author Cody Lerum
 * 
 */
public class AuditLog {

    @Inject
    private EntityManager em;

    @Inject
    private SessionUser sessionUser;

    public void log(@Observes EventLog e) {
        e.setUser(em.getReference(User.class, sessionUser.getUser().getId()));
        e.setRemoteAddr(sessionUser.getRemoteAddr());
        em.persist(e);
    }
}
