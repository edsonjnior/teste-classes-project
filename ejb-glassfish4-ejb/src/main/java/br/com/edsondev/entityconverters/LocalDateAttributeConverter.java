/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edsondev.entityconverters;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author junior
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
	if (localDate != null) {
	    return Date.valueOf(localDate);
	} else {
	    return null;
	}
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
	if (sqlDate != null) {
	    return sqlDate.toLocalDate();
	} else {
	    return null;
	}
    }

}