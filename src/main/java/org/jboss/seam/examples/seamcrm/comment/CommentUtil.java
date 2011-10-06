package org.jboss.seam.examples.seamcrm.comment;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.examples.seamcrm.aaa.SessionUser;
import org.jboss.seam.examples.seamcrm.core.BaseEntity;
import org.jboss.solder.logging.Logger;

/**
 * 
 * @author Cody Lerum
 * 
 */
@RequestScoped
@Named
public class CommentUtil implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private SessionUser activeUser;

    private Comment comment;

    @Produces
    @Named
    public Comment newComment()
    {
        comment = new Comment();
        return comment;
    }

    public void add(BaseEntity baseEntity)
    {
        comment.setUser(activeUser.getUser());
        comment.setEntityType(baseEntity.getEntityType());
        comment.setEntityId(baseEntity.getEntityId());
        em.persist(comment);
        em.flush();
        log.info("Comment Added for " + baseEntity.getEntityType() + " <" + baseEntity.getEntityId() + " >");
        comment = new Comment();
    }

}
