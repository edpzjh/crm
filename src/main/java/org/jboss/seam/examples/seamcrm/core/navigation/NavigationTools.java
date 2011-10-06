package org.jboss.seam.examples.seamcrm.core.navigation;

import org.jboss.seam.examples.seamcrm.core.BaseEntity;
import org.jboss.seam.examples.seamcrm.enumerations.EntityType;

public class NavigationTools {

    public static String buildRedirectViewId(EntityType entityType, String entityId) {
        return entityType.getViewId() + "?oid=" + entityId + "&faces-redirect=true";
    }

    public static String buildRedirectViewId(BaseEntity baseEntity)
    {
        return buildRedirectViewId(baseEntity.getEntityType(), baseEntity.getEntityId());
    }
}
