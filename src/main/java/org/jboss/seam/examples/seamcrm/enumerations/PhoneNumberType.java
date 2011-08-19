package org.jboss.seam.examples.seamcrm.enumerations;
/**
 * 
 * @author Cody Lerum
 * 
 */
public enum PhoneNumberType {
    WORK("Work"),
    MOBILE("Mobile"),
    FAX("Fax"),
    HOME("Home"),
    PAGER("Pager"),
    OTHER("Other");

    private String displayName;

    private PhoneNumberType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
