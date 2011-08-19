package org.jboss.seam.examples.seamcrm.aaa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.jboss.seam.examples.seamcrm.contact.Contact;
import org.jboss.seam.examples.seamcrm.enumerations.ContactType;
import org.jboss.seam.examples.seamcrm.tools.StringTools;

import com.google.common.base.Strings;

/**
 * 
 * @author Cody Lerum
 * 
 */
@ApplicationScoped
@Named
public class ContactTools {

	public static String buildGravatarURL(Contact c, int pixles) {
		String value = "";

		if (c != null && !Strings.isNullOrEmpty(c.getEmailAddress())) {
			value = c.getEmailAddress();
		}

		String hash = StringTools.getMD5Hex(value);

		return "https://secure.gravatar.com/avatar/" + hash + "?d=mm&s="
				+ pixles;
	}

	public static String buildName(Contact contact) {
		if (contact == null) {
			return null;
		} else if (contact.getType() == ContactType.PERSON) {
			return buildFullName(contact);
		} else if (contact.getType() == ContactType.ROLE) {
			return contact.getRoleName();
		} else {
			return "Unknown Name";
		}
	}

	public static String buildFullName(Contact contact) {
		if (contact == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		if (contact.getHonorific() != null) {
			sb.append(contact.getHonorific().getShortName());
			sb.append(". ");
		}

		sb.append(contact.getFirstName());
		sb.append(" ");
		sb.append(contact.getLastName());

		return sb.toString();
	}
}
