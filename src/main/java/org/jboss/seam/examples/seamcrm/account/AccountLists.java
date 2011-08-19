package org.jboss.seam.examples.seamcrm.account;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

public class AccountLists implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Produces
	@ConversationScoped
	@Named
	public List<Account> allAccounts() {
		return em.createQuery("from Account order by name asc", Account.class)
				.getResultList();
	}
}
