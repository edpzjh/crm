package org.jboss.seam.examples.seamcrm.core.search;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.jboss.logging.Logger;
import org.jboss.seam.examples.seamcrm.core.IndexedEntity;
import org.jboss.seam.international.status.Messages;



@Named
@RequestScoped
public class SearchUtil {

    private Version version = Version.LUCENE_31;

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Messages messages;
    
    @Inject
    private SearchParameters searchParameters;

    @SuppressWarnings("unchecked")
    public List<IndexedEntity> search(String queryString) {

        List<IndexedEntity> results;

        Query query;

        MultiFieldQueryParser standardParser = new MultiFieldQueryParser(version, searchParameters.getGlobalIndexedFields(), new StandardAnalyzer(version));

        try {
            query = standardParser.parse(queryString);
        } catch (ParseException e) {
            messages.error("Error parsing Query! " + e.getMessage());
            return new ArrayList<IndexedEntity>();
        }

        results = Search.getFullTextEntityManager(em).createFullTextQuery(query, IndexedEntity.class).getResultList();

        log.info("Searched for: " + queryString + " result Size: " + results.size());

        return results;
    }
    
    
    @SuppressWarnings("unchecked")
    public <T extends IndexedEntity> List<T> search(String queryString, Class<T> targetClass, String [] indexedFields)
    {
        List<T> results;

        Query query;

        MultiFieldQueryParser standardParser = new MultiFieldQueryParser(version, indexedFields, new StandardAnalyzer(version));

        try {
            query = standardParser.parse(queryString);
        } catch (ParseException e) {
            messages.error("Error parsing Query! " + e.getMessage());
            return new ArrayList<T>();
        }

        results = Search.getFullTextEntityManager(em).createFullTextQuery(query, targetClass).getResultList();
        
        log.info("Searched for: " + queryString + " result Size: " + results.size());

        return results;
    }
    
    
    

    public void reindex() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        fullTextEntityManager.createIndexer().startAndWait();
    }
}
