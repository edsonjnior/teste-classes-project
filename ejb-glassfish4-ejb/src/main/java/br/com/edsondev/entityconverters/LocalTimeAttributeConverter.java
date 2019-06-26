/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edsondev.entityconverters;

import java.sql.Time;
import java.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author junior
 */
@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
	if (localTime != null) {
	    return Time.valueOf(localTime);
	} else {
	    return null;
	}
    }

    @Override
    public LocalTime convertToEntityAttribute(Time sqlTime) {
	if (sqlTime != null) {
	    return sqlTime.toLocalTime();
	} else {
	    return null;
	}
    }
}
