package org.jboss.seam.examples.seamcrm.enumerations;
/**
 * 
 * @author Cody Lerum
 * 
 */
public enum State {
	AL("Alabama", Country.US, "US-AL"),
	AK("Alaska", Country.US, "US-AK"),
	AZ("Arizona", Country.US, "US-AZ"),
	AR("Arkansas", Country.US, "US-AR"),
	CA("California", Country.US, "US-CA"),
	CO("Colorado", Country.US, "US-CO"),
	CT("Connecticut", Country.US, "US-CT"),
	DE("Delaware", Country.US, "US-DE"),
	DC("District of Columbia", Country.US, "US-DC"),
	FL("Florida", Country.US, "US-FL"),
	GA("Georgia", Country.US, "US-GA"),
	HI("Hawaii", Country.US, "US-HI"),
	ID("Idaho", Country.US, "US-ID"),
	IL("Illinois", Country.US, "US-IL"),
	IN("Indiana", Country.US, "US-IN"),
	IA("Iowa", Country.US, "US-IA"),
	KS("Kansas", Country.US, "US-KS"),
	KY("Kentucky", Country.US, "US-KY"),
	LA("Louisiana", Country.US, "US-LA"),
	ME("Maine", Country.US, "US-ME"),
	MD("Maryland", Country.US, "US-MD"),
	MA("Massachusetts", Country.US, "US-MA"),
	MI("Michigan", Country.US, "US-MI"),
	MN("Minnesota", Country.US, "US-MN"),
	MS("Mississippi", Country.US, "US-MS"),
	MO("Missouri", Country.US, "US-MO"),
	MT("Montana", Country.US, "US-MT"),
	NE("Nebraska", Country.US, "US-NE"),
	NV("Nevada", Country.US, "US-NV"),
	NH("New Hampsire", Country.US, "US-NH"),
	NJ("New Jersey", Country.US, "US-NJ"),
	NM("New Mexico", Country.US, "US-NM"),
	NY("New York", Country.US, "US-NY"),
	NC("North Carolina", Country.US, "US-NC"),
	ND("North Dakota", Country.US, "US-ND"),
	OH("Ohio", Country.US, "US-OH"),
	OK("Oklahoma", Country.US, "US-OK"),
	OR("Oregon", Country.US, "US-OR"),
	PA("Pennsylvania", Country.US, "US-PA"),
	RI("Rhode Island", Country.US, "US-RI"),
	SC("South Carolina", Country.US, "US-SC"),
	SD("South Dakota", Country.US, "US-SD"),
	TN("Tennessee", Country.US, "US-TN"),
	TX("Texas", Country.US, "US-TX"),
	UT("Utah", Country.US, "US-UT"),
	VT("Vermont", Country.US, "US-VT"),
	VA("Virgina", Country.US, "US-VA"),
	WA("Washington", Country.US, "US-WA"),
	WV("West Virginia", Country.US, "US-WV"),
	WI("Wisconsin", Country.US, "US-WI"),
	WY("Wyoming", Country.US, "US-WY"),
	AS(Country.AS.getDisplayName(), Country.AS, "US-AS"),
	GU(Country.GU.getDisplayName(), Country.GU, "US-GU"),
	PR(Country.PR.getDisplayName(), Country.PR, "US-PR"),
	VI(Country.VI.getDisplayName(), Country.VI, "US-VI"),
	AB("Alberta", Country.CA, "CA-AB"),
	BC("British Columbia", Country.CA, "CA-BC"),
	MB("Manitoba", Country.CA, "CA-MB"),
	NB("New Brunswick", Country.CA, "CA-NB"),
	NL("Newfoundland and Labrador", Country.CA, "CA-NL"),
	NS("Nova Scotia", Country.CA, "CA-NS"),
	NT("Northwest Territories", Country.CA, "CA-NT"),
	NU("Nunavut", Country.CA, "CA-NU"),
	ON("Ontario", Country.CA, "CA-ON"),
	PE("Prince Edward Island", Country.CA, "CA-PE"),
	QC("Quebec", Country.CA, "CA-QC"),
	SK("Saskatchewan", Country.CA, "CA-SK"),
	YT("Yukon", Country.CA, "CA-YT");

	private String displayName;
	private Country country;
	private String isoCode;

	private State(String displayName, Country country, String isoCode) {
		this.displayName = displayName;
		this.country = country;
		this.isoCode = isoCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Country getCountry() {
		return country;
	}

	public String getIsoCode() {
		return isoCode;
	}
}
