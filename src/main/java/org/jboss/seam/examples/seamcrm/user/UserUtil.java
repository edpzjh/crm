package org.jboss.seam.examples.seamcrm.user;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.examples.seamcrm.core.Existing;
import org.jboss.seam.examples.seamcrm.core.navigation.NavigationTools;
import org.jboss.solder.logging.Logger;


@ConversationScoped
public class UserUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    @org.jboss.solder.servlet.http.RequestParam
    @Inject
    private Instance<String> oid;

    @Inject
    private Logger log;

    private User user;

    @Produces
    @ConversationScoped
    @Existing
    @Named
    public User user() {

        String id = oid.get();

        log.info("Running Contact Producer with oid: " + id);
        user = em.find(User.class, Integer.valueOf(id));

        return user;
    }

    public String update() {
        em.flush();
        return NavigationTools.buildRedirectViewId(user.getContact());
    }
}
