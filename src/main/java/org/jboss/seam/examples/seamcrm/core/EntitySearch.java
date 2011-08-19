package org.jboss.seam.examples.seamcrm.core;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.examples.seamcrm.account.Account;
import org.jboss.seam.examples.seamcrm.core.search.SearchParameters;
import org.jboss.seam.examples.seamcrm.core.search.SearchUtil;


@RequestScoped
@Named
public class EntitySearch {
    
    @Inject
    private SearchUtil searchUtil;

    @Inject
    private SearchParameters searchParameters;

    public List<Account> account(String value) {
        return searchUtil.search(value + "~", Account.class, searchParameters.getIndexedFields(Account.class));
    }

}
