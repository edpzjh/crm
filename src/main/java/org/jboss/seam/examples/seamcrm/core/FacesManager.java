package org.jboss.seam.examples.seamcrm.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Allows for controlling the redirect to a new page with paramaters and altering the existing conversation
 * 
 * @author Cody Lerum
 * 
 */
public class FacesManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private HttpServletResponse response;

    @Inject
    private HttpServletRequest request;

    @Inject
    private Conversation conversation;

    private boolean endConversation;
    private boolean includeConversationId;
    private String viewId;
    private Map<String, Object> parameters;

    /**
     * Execute the redirect
     * 
     * @throws IOException
     */
    public void redirect() throws IOException {
        if (viewId == null) {
            throw new RuntimeException("ViewID was not set");
        }

        if (includeConversationId) {
            parameters.put("cid", conversation.getId());
        }

        String encodedParameters = encodeParameters();

        if (endConversation && !conversation.isTransient()) {
            conversation.end();
        }

        if (encodedParameters != null) {
            response.sendRedirect(request.getContextPath() + viewId + encodedParameters);
        } else {
            response.sendRedirect(request.getContextPath() + viewId);
        }

        resetToDefaults();
    }

    /**
     * Redirect the to an external URL. Not to a view-id
     * 
     * @param url
     * @throws IOException
     */
    public void redirectToExternalURL(String url) throws IOException {
        response.sendRedirect(url);
    }

    /**
     * Set the ViewId
     * 
     * @param viewId
     * @return
     */
    public FacesManager viewId(String viewId) {
        this.viewId = viewId;
        return this;
    }

    /**
     * End the existing Conversation if it is long running.
     * 
     * @return
     */
    public FacesManager endConversation() {
        endConversation = true;
        return this;
    }

    /**
     * Include the existing Conversation id "cid" in the viewId
     * 
     * @return
     */
    public FacesManager includeConversationId() {
        includeConversationId = true;
        return this;
    }

    /**
     * Add a name/value pair to the viewId. Example home.xhtml?oid=6
     * 
     * @param key
     * @param value
     * @return
     */
    public FacesManager param(String name, Object value) {
        if (parameters == null) {
            parameters = new HashMap<String, Object>();
        }

        parameters.put(name, value);
        return this;
    }

    /**
     * Add a Map<String,Object> of params to the viewId
     * 
     * @param params
     * @return
     */
    public FacesManager params(Map<String, Object> params) {
        if (parameters == null) {
            parameters = new HashMap<String, Object>();
        }

        parameters.putAll(params);
        return this;
    }

    private String encodeParameters() {

        if (parameters == null || parameters.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        for (Entry<String, Object> p : parameters.entrySet()) {
            if (sb.length() == 0) {
                sb.append("?");
            } else {
                sb.append("&");
            }

            sb.append(p.getKey());
            sb.append("=");
            sb.append(p.getValue().toString());
        }

        return sb.toString();
    }

    private void resetToDefaults() {
        parameters.clear();
        endConversation = false;
        includeConversationId = false;
        viewId = null;
    }
}