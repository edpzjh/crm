package org.jboss.seam.examples.seamcrm.enumerations;

/**
 * 
 * @author Cody Lerum
 * 
 */
public enum AddressType implements BaseEnum {
    WORK("Work"), 
    HOME("Home");

    private String displayName;

    private AddressType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
