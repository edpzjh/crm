package org.jboss.seam.examples.seamcrm.exceptions;

import javax.enterprise.context.NonexistentConversationException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.jboss.seam.conversation.spi.SeamConversationContext;
import org.jboss.seam.exception.control.CaughtException;
import org.jboss.seam.exception.control.Handles;
import org.jboss.seam.exception.control.HandlesExceptions;

@HandlesExceptions
public class NavigationHandler {

	@Inject
	private Logger log;
	
	@Inject
	private SeamConversationContext<HttpServletRequest> seamConversationContext;
	
	@Inject
	private javax.faces.application.NavigationHandler nav;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest httpServletRequest;
	
	void nonexistentConversationException(@Handles CaughtException<NonexistentConversationException> e)
	{
		log.info("Conversation doesn't exist anymore");
		e.markHandled();
		seamConversationContext.dissociate(httpServletRequest);
		nav.handleNavigation(facesContext, null, "/home.xhtml");
	}
	
	void viewExpiredException(@Handles CaughtException<ViewExpiredException> e)
	{
		log.info("View has Expired");
		e.markHandled();
		seamConversationContext.dissociate(httpServletRequest);
		nav.handleNavigation(facesContext, null, "/home.xhtml");
	}	
}
