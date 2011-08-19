package org.jboss.seam.examples.seamcrm.aaa;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

/**
 * 
 * @author Cody Lerum
 * 
 */
public class SecurityRules {
    public @Secures
    @Authenticated
    boolean loggedInChecker(Identity identity) {
        return identity.isLoggedIn();
    }
}
