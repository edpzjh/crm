package org.jboss.seam.examples.seamcrm.core;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.jboss.solder.core.ExtensionManaged;


/**
 * 
 * @author Cody Lerum
 * 
 */
public class SeamCRMRepositoryProducer {


	@ExtensionManaged
	@Produces
	@PersistenceUnit
	@ConversationScoped
	EntityManagerFactory producerField;
	
	@Produces
    @SeamCRMRepository
    @PersistenceContext
	EntityManager entityManager;
	
}
