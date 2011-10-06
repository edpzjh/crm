package org.jboss.seam.examples.seamcrm.comment;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.jboss.seam.examples.seamcrm.aaa.SessionUser;
import org.jboss.seam.examples.seamcrm.core.BaseEntity;
import org.jboss.solder.logging.Logger;

/**
 * 
 * @author Cody Lerum
 * 
 */
@Named
@RequestScoped
public class EntityComments
{

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    @Inject
    private SessionUser sessionUser;

    public List<Comment> fetch(BaseEntity baseEntity)
    {
        log.info("Fetching Comment List for: " + baseEntity.getEntityType() + " <" + baseEntity.getEntityId() + ">");

        return em.createQuery("from Comment c join fetch c.user where c.entityType=:entityType and c.entityId=:entityId order by c.dateCreated desc", Comment.class)
                .setParameter("entityType", baseEntity.getEntityType())
                .setParameter("entityId", baseEntity.getEntityId())
                .setFlushMode(FlushModeType.COMMIT)
                .getResultList();
    }
    
    public void comment(@Observes Comment comment)
    {
        comment.setUser(sessionUser.getUser());
        em.persist(comment);
    }

}
