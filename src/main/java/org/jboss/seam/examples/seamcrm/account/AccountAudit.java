package org.jboss.seam.examples.seamcrm.account;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.solder.logging.Logger;
import org.jboss.seam.examples.seamcrm.aaa.AuditTools;
import org.jboss.seam.examples.seamcrm.aaa.EventLog;
import org.jboss.seam.examples.seamcrm.tools.MultiLineStringBuilder;


public class AccountAudit {

    @Inject
    private Event<EventLog> eventLog;

    public void audit(Account a) {
        audit(a.getOriginal(), a);
    }

    private void audit(Account orig, Account cur) {

        MultiLineStringBuilder sb = new MultiLineStringBuilder();

        if (AuditTools.isChanged(orig.getName(), cur.getName())) {
            sb.newLine(AuditTools.changedString("Name", orig.getName(), cur.getName()));
        }

        if (AuditTools.isChanged(orig.getType(), cur.getType())) {
            sb.newLine(AuditTools.changedString("Type", orig.getType(), cur.getType()));
        }      

        if (sb.length() > 0) {
            eventLog.fire(new EventLog(cur, sb.toString()));
        }
    }
}
