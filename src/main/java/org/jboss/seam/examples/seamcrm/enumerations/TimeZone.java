package org.jboss.seam.examples.seamcrm.enumerations;
/**
 * 
 * @author Cody Lerum
 *
 */
public enum TimeZone implements BaseEnum {
    
    GMT("GMT", java.util.TimeZone.getTimeZone("GMT")),
    EST("Eastern - No DST", java.util.TimeZone.getTimeZone("EST")),
    EST5EDT("Eastern", java.util.TimeZone.getTimeZone("EST5EDT")),
    CST("Central - No DST", java.util.TimeZone.getTimeZone("CST")),
    CST6CDT("Central", java.util.TimeZone.getTimeZone("CST6CDT")),
    MST("Mountain - Arizona", java.util.TimeZone.getTimeZone("MST")),
    MST7MDT("Mountain", java.util.TimeZone.getTimeZone("MST7MDT")),
    PST8("Pacific - No DST", java.util.TimeZone.getTimeZone("PST")),
    PST8PDT("Pacific", java.util.TimeZone.getTimeZone("PST8PDT"));

    private String displayName;
    private java.util.TimeZone timeZone;

    private TimeZone(String displayName, java.util.TimeZone timeZone) {
        this.displayName = displayName;
        this.timeZone = timeZone;
    }

    public String getDisplayName() {
        return displayName;
    }

    public java.util.TimeZone getTimeZone() {
        return timeZone;
    }

    public String getId() {
        return this.toString();
    }
}
