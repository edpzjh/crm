package org.jboss.seam.examples.seamcrm.address;

public class AddressTools {

    public static String fullAddressSingleLine(Address address) {
        StringBuilder sb = new StringBuilder();

        sb.append(address.getAddress1());
        sb.append(" ");
        sb.append(address.getCity());
        sb.append(" ");
        sb.append(address.getState());
        sb.append(", ");
        sb.append(address.getPostalCode());

        return sb.toString();
    }

    public static String fullAddressSingleLineType(Address address) {
        return address.getType().getDisplayName() + " - " + fullAddressSingleLine(address);
    }
}
