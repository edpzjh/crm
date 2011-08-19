package org.jboss.seam.examples.seamcrm.aaa;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.examples.seamcrm.contact.Contact;

/**
 * 
 * @author Cody Lerum
 *
 */
public class SessionContactManager {

    @Inject
    private EntityManager em;

    @Produces
    @Authenticated
    @Named
    public Contact sessionContact(SessionUser sessionUser) {
        return em.find(Contact.class, sessionUser.getUser().getContact().getId());
    }

}
