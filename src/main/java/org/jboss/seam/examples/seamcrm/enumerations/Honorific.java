package org.jboss.seam.examples.seamcrm.enumerations;
/**
 * 
 * @author Cody Lerum
 *
 */
public enum Honorific implements BaseEnum {

    DR("Dr", "Doctor"),
    ATTY("Atty", "Attorney");

    private String shortName;
    private String displayName;

    private Honorific(String shortName, String displayName) {
        this.shortName = shortName;
        this.displayName = displayName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
