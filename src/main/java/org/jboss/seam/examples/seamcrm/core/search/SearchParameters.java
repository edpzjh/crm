package org.jboss.seam.examples.seamcrm.core.search;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.jboss.seam.examples.seamcrm.account.Account;
import org.jboss.seam.examples.seamcrm.address.Address;
import org.jboss.seam.examples.seamcrm.contact.Contact;
import org.jboss.seam.examples.seamcrm.core.IndexedEntity;


/**
 * 
 * @author Cody Lerum
 * 
 */
@ApplicationScoped
public class SearchParameters implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String[] indexedFields;
    private Set<Class<? extends IndexedEntity>> indexedClasses = new HashSet<Class<? extends IndexedEntity>>();
    private Map<Class<? extends IndexedEntity>, String[]> classIndexedFields = new HashMap<Class<? extends IndexedEntity>, String[]>();

    @Inject
    public void initialize(Logger log)
    {
        log.info("Initializing SearchParameters");
        Set<IndexedEntity> indexedObjects = getIndexedObjects();

        buildIndexedClasses(indexedObjects);
        buildIndexedFields(indexedObjects);

        indexedObjects = null;
    }

    private void buildIndexedClasses(Set<IndexedEntity> indexedObjects)
    {
        for (IndexedEntity ie : indexedObjects)
        {
            indexedClasses.add(ie.getClass());
        }
    }

    private void buildIndexedFields(Set<IndexedEntity> indexedObjects)
    {
        Set<String> fields = new HashSet<String>();
        for (IndexedEntity ie : indexedObjects)
        {
            fields.addAll(Arrays.asList(ie.getIndexedFields()));
            classIndexedFields.put(ie.getClass(), ie.getIndexedFields());
        }
        indexedFields = fields.toArray(new String[fields.size()]);
    }

    private static Set<IndexedEntity> getIndexedObjects()
    {
        Set<IndexedEntity> indexedObjects = new HashSet<IndexedEntity>();
        indexedObjects.add(new Address());
        indexedObjects.add(new Account());
        indexedObjects.add(new Contact());
        return indexedObjects;
    }

    public String[] getGlobalIndexedFields()
    {
        return indexedFields;
    }

    public Set<Class<? extends IndexedEntity>> getIndexedClasses()
    {
        return indexedClasses;
    }

    public <T extends IndexedEntity> String[] getIndexedFields(Class<T> indexedEntityClass)
    {
        return classIndexedFields.get(indexedEntityClass);
    }

}
