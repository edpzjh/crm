package org.jboss.seam.examples.seamcrm.aaa;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.examples.seamcrm.core.SeamCRMRepository;
import org.jboss.seam.examples.seamcrm.tools.StringTools;
import org.jboss.seam.examples.seamcrm.user.User;

/**
 * 
 * @author Cody Lerum
 * 
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AuditLog {
    @Inject
    private Instance<EntityManager> entityManagerInstance;

    @Inject
    @SeamCRMRepository
    private Instance<EntityManager> entityManagerNoContextInstance;

    @Inject
    private Instance<SessionUser> sessionUserInstance;

    public void log(@Observes EventLog eventLog) {
        if (!StringTools.isNullOrBlank(eventLog.getMessage())) {
            try {
                EntityManager em = entityManagerInstance.get();
                SessionUser sessionUser = sessionUserInstance.get();
                eventLog.setUser(em.getReference(User.class, sessionUser.getUser().getId()));
                eventLog.setRemoteAddr(sessionUser.getRemoteAddr());
                em.persist(eventLog);
            } catch (ContextNotActiveException e) {
                EntityManager em = entityManagerNoContextInstance.get();
                eventLog.setUser(em.getReference(User.class, 1));
                eventLog.setRemoteAddr("::0");
                em.persist(eventLog);
            }
        }
    }
}
