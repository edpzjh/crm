package org.jboss.seam.examples.seamcrm.tools;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.jboss.seam.examples.seamcrm.account.AccountType;
import org.jboss.seam.examples.seamcrm.enumerations.AddressType;
import org.jboss.seam.examples.seamcrm.enumerations.ContactType;
import org.jboss.seam.examples.seamcrm.enumerations.County;
import org.jboss.seam.examples.seamcrm.enumerations.Honorific;
import org.jboss.seam.examples.seamcrm.enumerations.State;
import org.jboss.seam.examples.seamcrm.enumerations.TimeZone;

/**
 * 
 * @author Cody Lerum
 *
 */
@Named
@ApplicationScoped
public class EnumValues {

    public List<AccountType> getAccountType() {
        return Arrays.asList(AccountType.values());
    }

    public List<ContactType> getContactType() {
        return Arrays.asList(ContactType.values());
    }

    public List<Honorific> getHonorific() {
        return Arrays.asList(Honorific.values());
    }

    public List<TimeZone> getTimeZone() {
        return Arrays.asList(TimeZone.values());
    }

    public List<State> getState() {
        return Arrays.asList(State.values());
    }

    public List<County> getCounty() {
        return Arrays.asList(County.values());
    }

    public List<AddressType> getAddressType() {
        return Arrays.asList(AddressType.values());
    }

}
