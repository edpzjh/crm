package org.jboss.seam.examples.seamcrm.testdata;

import java.security.GeneralSecurityException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;
import org.jboss.seam.examples.seamcrm.aaa.SecurityUtil;
import org.jboss.seam.examples.seamcrm.account.Account;
import org.jboss.seam.examples.seamcrm.account.AccountType;
import org.jboss.seam.examples.seamcrm.contact.Contact;
import org.jboss.seam.examples.seamcrm.core.SeamCRMRepository;
import org.jboss.seam.examples.seamcrm.enumerations.ContactType;
import org.jboss.seam.examples.seamcrm.enumerations.TimeZone;
import org.jboss.seam.examples.seamcrm.tools.CalendarTools;
import org.jboss.seam.examples.seamcrm.user.User;


/**
 * 
 * @author Cody Lerum
 * 
 */
@Startup
@Singleton
public class SeedDataImporter {

	@Inject
	private Logger log;

	@Inject
	@SeamCRMRepository
	private EntityManager em;

	@Inject
	private SecurityUtil securityUtil;
	
	private Account widgets;
	private Account gadgets;

	@PostConstruct
	public void importData() throws GeneralSecurityException {

		loadTestUsers();
		log.info("Seed Data Loaded");
	}

	private void loadTestUsers() throws GeneralSecurityException {
       loadAccounts();
		loadCody();
		loadDemo();
	}

	private void loadCody() throws GeneralSecurityException {
		Contact c = new Contact();
		c.setFirstName("Seamy");
		c.setLastName("Seamerson");
		c.setEmailAddress("seamey@seamframework.org");
		c.setType(ContactType.PERSON);
		c.setTimeZone(TimeZone.MST7MDT);
	    c.setAccount(widgets);

		em.persist(c);

		User u = new User();
		u.setDateCreated(CalendarTools.nowNoMilliseconds());
		String hash = securityUtil.hash(u, "seamrocks");
		u.setPassword(hash);
		u.setContact(c);

		em.persist(u);
	}

	private void loadDemo() throws GeneralSecurityException {
		Contact c = new Contact();
		c.setFirstName("Demo");
		c.setLastName("User");
		c.setEmailAddress("demo@test.com");
		c.setType(ContactType.PERSON);
		c.setTimeZone(TimeZone.MST7MDT);
		c.setAccount(widgets);

		em.persist(c);

		User u = new User();
		u.setDateCreated(CalendarTools.nowNoMilliseconds());
		u.setPassword(securityUtil.hash(u, "demo"));
		u.setContact(c);

		em.persist(u);
	}

	private void loadAccounts() {
		widgets();
		gadgets();
		mattDamon();
	}

	private void widgets() {
		Account a = new Account();

		a.setName("Widgets, Inc");
		a.setType(AccountType.BUSINESS);

		em.persist(a);
		widgets = a;
	}

	private void gadgets() {
		Account a = new Account();

		a.setName("Gadgets LLC");
		a.setType(AccountType.BUSINESS);

		em.persist(a);
		gadgets = a;
	}

	private void mattDamon() {
		Account a = new Account();

		a.setName("Matt Damon");
		a.setType(AccountType.INDIVIDUAL);

		em.persist(a);
	}
}
