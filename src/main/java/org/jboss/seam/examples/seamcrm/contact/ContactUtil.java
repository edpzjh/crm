package org.jboss.seam.examples.seamcrm.contact;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.solder.logging.Logger;
import org.jboss.solder.servlet.http.RequestParam;
import org.jboss.seam.examples.seamcrm.aaa.EntityLogs;
import org.jboss.seam.examples.seamcrm.aaa.EventLog;
import org.jboss.seam.examples.seamcrm.account.Account;
import org.jboss.seam.examples.seamcrm.comment.Comment;
import org.jboss.seam.examples.seamcrm.comment.EntityComments;
import org.jboss.seam.examples.seamcrm.core.Existing;
import org.jboss.seam.examples.seamcrm.core.navigation.NavigationTools;

@Named
@ConversationScoped
public class ContactUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;
    
    @Inject
    private Conversation conversation;

    @RequestParam
    @Inject
    private Instance<String> oid;

    @Inject
    private Logger log;

    @Existing
    @Inject
    private Instance<Account> accountInstance;

    @Inject
    private Event<EventLog> eventLog;

    private Contact contact;

    @Produces
    @ConversationScoped
    @Existing
    @Named
    public Contact contact() {

        String id = oid.get();

        log.info("Running Contact Producer with oid: " + id);
        contact = em.find(Contact.class, Integer.valueOf(id));

        return contact;
    }

    @Produces
    @ConversationScoped
    @Named
    public Contact newContact() {
        log.info("Running Contact Producer for new");
        contact = new Contact();
        contact.setAccount(accountInstance.get());
        return contact;
    }

    @Produces
    @RequestScoped
    @Named
    public List<EventLog> contactEventLogs(EntityLogs entityLogs) {
        return entityLogs.fetch(contact);
    }
    
    @Produces
    @RequestScoped
    @Named
    public List<Comment> contactComments(EntityComments entityComments) {
        return entityComments.fetch(contact);
    }

    public String add() {

        em.persist(contact);
        eventLog.fire(new EventLog(contact, "Contact Created"));
        em.flush();
        conversation.end();
        return NavigationTools.buildRedirectViewId(contact);
    }

    public String update() {
        em.flush();
        conversation.end();
        return NavigationTools.buildRedirectViewId(contact);
    }
}
