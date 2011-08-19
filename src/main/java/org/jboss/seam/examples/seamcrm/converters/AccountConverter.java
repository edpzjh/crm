package org.jboss.seam.examples.seamcrm.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.examples.seamcrm.account.Account;
import org.jboss.seam.examples.seamcrm.tools.StringTools;


@FacesConverter("accountConverter")
public class AccountConverter implements Serializable, Converter {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    @Override
    public Object getAsObject(final FacesContext arg0, final UIComponent arg1, final String id) {

        if (!StringTools.isNullOrBlank(id)) {
            return em.find(Account.class, Integer.valueOf(id));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(final FacesContext context, final UIComponent comp, final Object object) {
        if (object != null) {
            Account account = (Account) object;
            return account.getEntityId();
        } else {
            return null;
        }
    }
}
