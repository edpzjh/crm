package org.jboss.seam.examples.seamcrm.core;

public interface IndexedEntity extends BaseEntity {

    public String[] getIndexedFields();
    public String getSearchResultName();
}
