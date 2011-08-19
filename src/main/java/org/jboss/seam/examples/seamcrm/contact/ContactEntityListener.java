package org.jboss.seam.examples.seamcrm.contact;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.persistence.PreUpdate;

import org.jboss.seam.solder.beanManager.BeanManagerAware;

public class ContactEntityListener extends BeanManagerAware {
	
	@PreUpdate
	public void preUpdate(Contact contact) {
		audit(contact);
	}
	
	private void audit(Contact contact)
	{
		BeanManager bm = getBeanManager();
		Bean<?> beans = bm.resolve(bm.getBeans(ContactAudit.class));
		ContactAudit contactAudit = (ContactAudit) bm.getReference(beans, ContactAudit.class, bm.createCreationalContext(beans));  
		contactAudit.audit(contact);
	}
	
}
