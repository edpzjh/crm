package org.jboss.seam.examples.seamcrm.aaa;

import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

/**
 * 
 * @author Cody Lerum
 * 
 */
@ViewConfig
public interface AppViewConfig {

    static enum Pages {

        @FacesRedirect
        @ViewPattern("/admin/*")
        @LoginView("/login.xhtml")
        @Authenticated
        SECURED,
    }
}
