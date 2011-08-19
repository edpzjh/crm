package org.jboss.seam.examples.seamcrm.enumerations;

/**
 * 
 * @author Cody Lerum
 * 
 */
public enum EntityType {
    ADDRESS("Address", "/admin/account/address/address_view.xhtml", null),
    ACCOUNT("Address", "/admin/account/account_view.xhtml", null), 
    CONTACT("Contact", "/admin/contact/contact_view.xhtml", null), 
    PHONE_NUMBER("Phone Number", null, null), 
    USER("User", null, null);
    
    private String displayName;
    private String viewId;
    private String icon;

    private EntityType(String displayName, String viewId, String icon) {
        this.displayName = displayName;
        this.viewId = viewId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getViewId() {
        return viewId;
    }
    
    public String getIcon() {
        return icon;
    }
}
