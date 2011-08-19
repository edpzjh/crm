package org.jboss.seam.examples.seamcrm.contact;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.seam.examples.seamcrm.aaa.AuditTools;
import org.jboss.seam.examples.seamcrm.aaa.EventLog;
import org.jboss.seam.examples.seamcrm.tools.MultiLineStringBuilder;


public class ContactAudit {

    @Inject
    private Event<EventLog> eventLog;

    public void audit(Contact c) {
        audit(c.getOriginal(), c);
    }

    private void audit(Contact orig, Contact cur) {

        MultiLineStringBuilder sb = new MultiLineStringBuilder();

        if (AuditTools.isChanged(orig.getFirstName(), cur.getFirstName())) {
            sb.newLine(AuditTools.changedString("First Name", orig.getFirstName(), cur.getFirstName()));
        }
        
        if (AuditTools.isChanged(orig.getLastName(), cur.getLastName())) {
            sb.newLine(AuditTools.changedString("Last Name", orig.getLastName(), cur.getLastName()));
        }
        
        if (AuditTools.isChanged(orig.getRoleName(), cur.getRoleName())) {
            sb.newLine(AuditTools.changedString("Role Name", orig.getRoleName(), cur.getRoleName()));
        }
        
        if (AuditTools.isChanged(orig.getEmailAddress(), cur.getEmailAddress())) {
            sb.newLine(AuditTools.changedString("Email Address", orig.getEmailAddress(), cur.getEmailAddress()));
        }
        
        if (AuditTools.isChanged(orig.getAlias(), cur.getAlias())) {
            sb.newLine(AuditTools.changedString("Alias", orig.getAlias(), cur.getAlias()));
        }

        if (AuditTools.isChanged(orig.getTitle(), cur.getTitle())) {
            sb.newLine(AuditTools.changedString("Title", orig.getTitle(), cur.getTitle()));
        }
        
        if (AuditTools.isChanged(orig.getHonorific(), cur.getHonorific())) {
            sb.newLine(AuditTools.changedString("Honorific", orig.getHonorific(), cur.getHonorific()));
        }
        
        if (AuditTools.isChanged(orig.getTimeZone(), cur.getTimeZone())) {
            sb.newLine(AuditTools.changedString("Time Zone", orig.getTimeZone(), cur.getTimeZone()));
        }

        if (sb.length() > 0) {
            eventLog.fire(new EventLog(cur, sb.toString()));
        }
    }
}
