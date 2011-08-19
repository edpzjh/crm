package org.jboss.seam.examples.seamcrm.enumerations;
/**
 * 
 * @author Cody Lerum
 *
 */
public enum DateFormatStyle {
    
    SHORT("MM/dd/yyyy"),
    MEDIUM("MM/dd/yyyy HH:mm z"),
    LONG("MM/dd/yyyy HH:mm:ss z");
    
    private String formatPattern;

    private DateFormatStyle(String formatPattern) {
        this.formatPattern = formatPattern;
    }

    public String getFormatPattern() {
        return formatPattern;
    }
}
