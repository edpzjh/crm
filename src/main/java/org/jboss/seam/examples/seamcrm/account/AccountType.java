package org.jboss.seam.examples.seamcrm.account;

import org.jboss.seam.examples.seamcrm.enumerations.BaseEnum;

/**
 * 
 * @author Cody Lerum
 * 
 */
public enum AccountType implements BaseEnum {

    BUSINESS("Business"), 
    INDIVIDUAL("Individual");

    private String displayName;

    private AccountType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
