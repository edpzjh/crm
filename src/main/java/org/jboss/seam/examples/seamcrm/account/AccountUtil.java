package org.jboss.seam.examples.seamcrm.account;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.jboss.solder.logging.Logger;
import org.jboss.solder.servlet.http.RequestParam;
import org.jboss.seam.examples.seamcrm.aaa.EntityLogs;
import org.jboss.seam.examples.seamcrm.aaa.EventLog;
import org.jboss.seam.examples.seamcrm.core.Existing;


/**
 * 
 * @author Cody Lerum
 * 
 */
@Named
@ConversationScoped
public class AccountUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    @Inject
    private Logger log;

    @Inject
    private Conversation conversation;
    
    @RequestParam
    @Inject
    private Instance<String> oid;

    @Inject
    private HttpServletRequest request;

    @Inject
    @Existing
    private Account account;

    @Produces
    @ConversationScoped
    @Existing
    @Named
    public Account account()
    {
        log.info("Running Account Producer");
        account = em.find(Account.class, Integer.valueOf(oid.get()));
        return account;
    }

    @Produces
    @RequestScoped
    @Named
    public List<EventLog> accountEventLogs(EntityLogs entityLogs) {
        return entityLogs.fetch(account);
    }

    public String update() {
        em.flush();
        conversation.end();
        return "success";
    }
}
