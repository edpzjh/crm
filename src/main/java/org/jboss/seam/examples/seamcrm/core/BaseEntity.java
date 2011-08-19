package org.jboss.seam.examples.seamcrm.core;

import org.jboss.seam.examples.seamcrm.enumerations.EntityType;

/**
 * 
 * @author Cody Lerum
 * 
 */
public interface BaseEntity {

    public EntityType getEntityType();

    public String getEntityId();
    
    public String getRecentViewName();
}
