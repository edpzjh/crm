package org.jboss.seam.examples.seamcrm.enumerations;
/**
 * 
 * @author Cody Lerum
 * 
 */
public enum Country
{
    US("United States"),
    AG("Antigua and Barbuda"),
    AI("Anguilla"),
    AS("American Samoa"),
    BB("Barbados"),
    BM("Bermuda"),
    BS("Bahamas"),
    CA("Canada"),
    DM("Dominica"),
    DO("Dominican Repulbic"),
    GD("Grenada"),
    GU("Guam"),
    JM("Jamaica"),
    KN("St. Kitts and Nevis"),
    KY("Cayman Islands"),
    LC("St. Lucia"),
    MP("North Mariana Islands"),
    MS("Montserrat"),
    MX("Mexico"),
    PR("Puerto Rico"),
    TC("Turks and Caicos Islands"),
    TT("Trinidad and Tobago"),
    VC("St. Vincent and the Grenadines"),
    VG("British Virgin Islands"),
    VI("U.S. Virgin Islands");

    private String displayName;

    private Country(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public static Country getValueByCountryCode(String input)
    {
        for (Country country : Country.values())
        {
            if (input.equalsIgnoreCase(country.toString()))
            {
                return country;
            }
        }
        return null;
    }
}