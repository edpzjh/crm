package org.jboss.seam.examples.seamcrm.aaa;

import java.io.Serializable;
import java.security.GeneralSecurityException;

import javax.inject.Inject;

import org.jboss.seam.examples.seamcrm.user.User;
import org.jboss.seam.security.management.PasswordHash;

import com.google.common.primitives.Longs;

public class SecurityUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PasswordHash passwordHash;

    public String hash(User u, String password) throws GeneralSecurityException {
        return passwordHash.createPasswordKey(password.toCharArray(), Longs.toByteArray(u.getDateCreated().getTime()), 7);
    }

}
