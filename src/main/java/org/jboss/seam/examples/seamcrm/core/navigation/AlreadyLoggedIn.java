package org.jboss.seam.examples.seamcrm.core.navigation;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.jboss.seam.security.Identity;

@Model
public class AlreadyLoggedIn {

    @Inject
    private Identity identity;

    public String alreadyLoggedIn() {

        if (identity.isLoggedIn()) {
            return "/admin/home.xhtml";
        }
        else
        {
            return null;
        }

    }

}
