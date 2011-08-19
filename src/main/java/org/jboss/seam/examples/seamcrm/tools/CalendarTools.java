package org.jboss.seam.examples.seamcrm.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Cody Lerum
 * 
 */
public class CalendarTools {

    public static Date nowNoMilliseconds() {
        GregorianCalendar now = nowCalendar();
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }

    public static GregorianCalendar nowCalendar() {
        return (GregorianCalendar) GregorianCalendar.getInstance();
    }

}
