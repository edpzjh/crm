package org.jboss.seam.examples.seamcrm.account;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.persistence.PreUpdate;

import org.jboss.seam.solder.beanManager.BeanManagerAware;

public class AccountEntityListener extends BeanManagerAware {
	
	@PreUpdate
	public void preUpdate(Account account) {
		audit(account);
	}
	
	private void audit(Account account)
	{
		BeanManager bm = getBeanManager();
		Bean<?> beans = bm.resolve(bm.getBeans(AccountAudit.class));
		AccountAudit accountAudit = (AccountAudit) bm.getReference(beans, AccountAudit.class, bm.createCreationalContext(beans));  
		accountAudit.audit(account);
	}
	
}
