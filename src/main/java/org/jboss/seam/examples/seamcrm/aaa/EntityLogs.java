package org.jboss.seam.examples.seamcrm.aaa;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.jboss.solder.logging.Logger;
import org.jboss.seam.examples.seamcrm.core.BaseEntity;


@Named
@RequestScoped
public class EntityLogs {
	
	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;	
	
	public List<EventLog> fetch(BaseEntity baseEntity)
	{
		log.info("Fetching Event Log List for: " + baseEntity.getEntityType() + " <" + baseEntity.getEntityId() + ">");
		return em.createQuery("from EventLog where entityType=:entityType and entityId=:entityId order by date desc", EventLog.class)
		.setParameter("entityType", baseEntity.getEntityType())
		.setParameter("entityId", baseEntity.getEntityId())
		.setMaxResults(50)
		.setFlushMode(FlushModeType.COMMIT)
		.getResultList();
	}
}
