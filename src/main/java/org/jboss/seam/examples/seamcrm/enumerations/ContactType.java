package org.jboss.seam.examples.seamcrm.enumerations;

public enum ContactType {
	PERSON("Person"),
	ROLE("Role");

	private String displayName;

	private ContactType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
