package org.jboss.seam.examples.seamcrm.address;

import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.solder.logging.Logger;
import org.jboss.solder.servlet.http.RequestParam;
import org.jboss.seam.examples.seamcrm.aaa.EntityLogs;
import org.jboss.seam.examples.seamcrm.aaa.EventLog;
import org.jboss.seam.examples.seamcrm.core.Existing;

/**
 * 
 * @author Cody Lerum
 *
 */
public class AddressUtil {

    @RequestParam
    @Inject
    private Instance<String> oid;

    @Inject
    private EntityManager em;

    @Inject
    private Logger log;

    @Inject
    @Existing
    private Address address;

    @Produces
    @ConversationScoped
    @Existing
    @Named
    public Address address() {

        String id = oid.get();

        log.info("Running Address Producer with oid: " + id);
        address = em.find(Address.class, Integer.valueOf(id));

        return address;
    }

    @Produces
    @RequestScoped
    @Named
    public List<EventLog> addressEventLogs(EntityLogs entityLogs) {
        return entityLogs.fetch(address);
    }
}
