package br.com.edsondev.converters;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("localTimeConverter")
public class LocalTimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (null == value || value.isEmpty()) {
	    return null;
	}

	LocalTime localTime = null;

	try {
	    localTime = LocalTime.parse(value.trim(), DateTimeFormatter.ofPattern("HH:mm"));
	} catch (DateTimeParseException e) {
	    throw new ConverterException("Formato incorreto. Exemplo: 12:00");
	}

	return localTime;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (null == value) {
	    return "";
	}

	return ((LocalTime) value).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
