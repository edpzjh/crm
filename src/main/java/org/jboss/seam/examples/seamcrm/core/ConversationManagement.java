package org.jboss.seam.examples.seamcrm.core;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

@RequestScoped
@Named
public class ConversationManagement {
	
	@Inject
	private Logger log;
	
	@Inject
	private Conversation conversation;	
	
	public String begin()
	{
		if(conversation.isTransient())
		{
			conversation.begin();
			log.info("Began Long Running Conversation <" + conversation.getId() + ">");
			return null;
		}
		else
		{
			log.info("Long Running Conversation <" + conversation.getId() + "> already active");
			return null;
		}
	}

}
