package br.com.edsondev.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("localDateConverter")
public class LocalDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (null == value || value.isEmpty()) {
	    return null;
	}

	LocalDate localDate = null;

	try {
	    localDate = LocalDate.parse(value.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	} catch (DateTimeParseException e) {
	    throw new ConverterException("Ano deve conter 4 caracteres. Exemplo: 01/01/2019");
	}

	return localDate;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (null == value) {
	    return "";
	}

	return ((LocalDate) value).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
