package org.jboss.seam.examples.seamcrm.converters;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.jboss.seam.examples.seamcrm.tools.StringTools;


@RequestScoped
@FacesConverter("StringTrim20Converter")
public class StringTrim20Converter implements Serializable, Converter {

    private static final long serialVersionUID = 1L;

    @Override
    public Object getAsObject(final FacesContext arg0, final UIComponent arg1, final String value) {

        return StringTools.stringTrim(value, 20, "...");
    }

    @Override
    public String getAsString(final FacesContext context, final UIComponent comp, final Object object) {
        return StringTools.stringTrim(object.toString(), 20, "...");
    }
}
