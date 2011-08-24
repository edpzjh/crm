package org.jboss.seam.examples.seamcrm.core;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.international.status.Messages;

@RequestScoped
@Named
public class NotifyTest {

    @Inject
    private Messages messages;

    public void info() {
        messages.info("This is an Info Message");
    }

    public void warn() {
        messages.warn("This is an Warn Message");
    }

    public void error() {
        messages.error("This is an Error Message");
    }

    public void fatal() {
        messages.fatal("This is an Fatal Message");
    }

}
