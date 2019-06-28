package br.com.edsondev.converters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (null == value || value.isEmpty()) {
	    return null;
	}

	LocalDateTime localDateTime = null;

	try {
	    localDateTime = LocalDateTime.parse(value.trim(),
		    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault()));
	} catch (DateTimeParseException e) {
	    throw new ConverterException("O formato da data e hora deve ser 01/01/2019 12:00.");
	}

	return localDateTime;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (null == value) {
	    return "";
	}

	return ((LocalDateTime) value)
		.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault()));
    }
}
