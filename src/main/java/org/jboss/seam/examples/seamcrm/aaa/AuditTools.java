package org.jboss.seam.examples.seamcrm.aaa;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.jboss.seam.examples.seamcrm.core.BaseEntity;
import org.jboss.seam.examples.seamcrm.enumerations.BaseEnum;
import org.jboss.seam.examples.seamcrm.enumerations.DateFormatStyle;
import org.jboss.seam.examples.seamcrm.tools.StringTools;


@RequestScoped
@Named
public class AuditTools
{

    public String firstLine(EventLog log)
    {
        return StringTools.firstLine(log.getMessage());
    }

    public List<String> additionalLines(EventLog log)
    {
        if (log != null)
        {
            return StringTools.additionalLines(log.getMessage());
        }
        else
        {
            return null;
        }
    }

    public boolean containsMultipleLines(EventLog log)
    {
        return StringTools.containsMultipleLines(log.getMessage());
    }

    public static boolean isChanged(BaseEntity o, BaseEntity n)
    {
        if (base(o, n) != null)
        {
            return base(o, n);
        }
        else
        {
            if (!o.getEntityId().equals(n.getEntityId()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static boolean isChanged(Date o, Date n)
    {
        if (base(o, n) != null)
        {
            return base(o, n);
        }
        else
        {
            return o.equals(n);
        }
    }

    /**
     * Null Safe check to see if Strings are different
     * 
     * @param o Original Value
     * @param n New Value
     * @return
     */
    public static boolean isChanged(String o, String n)
    {

        o = StringTools.normalize(o);
        n = StringTools.normalize(n);

        if (base(o, n) != null)
        {
            return base(o, n);
        }
        else
        {
            return !o.equals(n);
        }
    }

    public static boolean isChanged(BigDecimal o, BigDecimal n)
    {

        if (base(o, n) != null)
        {
            return base(o, n);
        }
        else
        {
            return !o.equals(n);
        }
    }

    public static boolean isChanged(boolean o, boolean n)
    {
        return !o == n;
    }

    /**
     * Null safe check to see if two Enums are different
     * 
     * @param <E>
     * @param originalEnum
     * @param newEnum
     * @return
     */
    public static <E extends Enum<E>> boolean isChanged(Enum<E> o, Enum<E> n)
    {
        if (base(o, n) != null)
        {
            return base(o, n);
        }
        else
        {
            if (o != n)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    private static Boolean base(Object o, Object n)
    {
        if (o == null && n == null)
        {
            return false;
        }
        else if (o == null || n == null)
        {
            return true;
        }
        else
        {
            return null;
        }
    }

    public static String changedString(String name, String originalValue, String newValue)
    {
        return name + " changed from " + normalize(originalValue) + " to " + normalize(newValue);
    }

    public static String changedString(String name, Boolean originalValue, Boolean newValue)
    {
        return changedString(name, normalize(originalValue), normalize(newValue));
    }

    public static String changedString(String name, BaseEntity originalValue, BaseEntity newValue)
    {
        return changedString(name, normalize(originalValue), normalize(newValue));
    }

    public static String changedString(String name, Number originalValue, Number newValue)
    {
        return changedString(name, normalize(originalValue), normalize(newValue));
    }

    public static String changedString(String name, Date originalValue, Date newValue, DateFormatStyle dateFormatStyle)
    {
        return changedString(name, normalize(originalValue), normalize(newValue));
    }

    public static String changedString(String name, BaseEnum originalValue, BaseEnum newValue)
    {
        return changedString(name, normalize(originalValue), normalize(newValue));
    }

    /**
     * Normalizes a string value to either it's value or "EMPTY" if null
     * 
     * @param value
     * @return
     */
    public static String normalize(String value)
    {
        if (value == null || value.trim().isEmpty())
        {
            return "EMPTY";
        }
        else
        {
            return value;
        }
    }

    /**
     * Normalizes a Enum value to either it's value or "EMPTY" if null
     * 
     * @param value
     * @return
     */
    public static <E extends Enum<E>> String normalize(Enum<E> value)
    {
        if (value == null)
        {
            return null;
        }
        else
        {
            return value.toString();
        }
    }

    /**
     * Normalizes a Enum value to either it's value or "EMPTY" if null
     * 
     * @param value
     * @return
     */
    public static String normalize(BaseEnum value)
    {
        if (value == null)
        {
            return null;
        }
        else
        {
            return value.getDisplayName();
        }
    }

    public static String normalize(Boolean value)
    {
        if (value == null)
        {
            return null;
        }
        else
        {
            return value.toString().toUpperCase();
        }
    }

    public static String normalize(Object value)
    {
        if (value == null)
        {
            return null;
        }
        else
        {
            return value.toString();
        }
    }

    public static String normalize(Date value, DateFormatStyle dateFormatStyle)
    {
        if (value == null)
        {
            return null;
        }
        else
        {
            return new SimpleDateFormat(dateFormatStyle.getFormatPattern()).format(value);
        }
    }
}
