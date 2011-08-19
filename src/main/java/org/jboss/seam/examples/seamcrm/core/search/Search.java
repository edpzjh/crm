package org.jboss.seam.examples.seamcrm.core.search;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.examples.seamcrm.core.FacesManager;
import org.jboss.seam.examples.seamcrm.core.IndexedEntity;
import org.jboss.seam.faces.context.RenderScoped;
import org.jboss.seam.international.status.Messages;


@RenderScoped
@Named
public class Search implements Serializable {

    private static final long serialVersionUID = 1L;

    private String query;
    private List<IndexedEntity> results;

    @Inject
    private Messages messages;

    @Inject
    private SearchUtil searchUtil;

    @Inject
    private Instance<FacesManager> facesManager;

    @Inject
    private Conversation conversation;

    public String search() throws IOException {

        results = searchUtil.search(query);

        if (results.isEmpty()) {
            messages.info("No Results Found");
            return null;
        }

        if (results.size() == 1 && results.get(0).getEntityType().getViewId() != null) {

            IndexedEntity entity = results.get(0);
            // TODO: Replace with Seam Faces Redirect Helper once is an BETA build

            if (!conversation.isTransient()) {
                conversation.end();
            }

            facesManager.get().viewId(entity.getEntityType().getViewId()).param("oid", entity.getEntityId()).endConversation()
                    .redirect();

            return null;
        }

        return "/admin/search/search.xhtml";
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<IndexedEntity> getResults() {
        System.out.println("Returning Result List of size: " + results.size());
        return results;
    }

    public void setResults(List<IndexedEntity> results) {
        this.results = results;
    }
}
