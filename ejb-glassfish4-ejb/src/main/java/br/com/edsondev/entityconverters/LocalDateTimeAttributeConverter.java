package br.com.edsondev.entityconverters;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author junior
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
	if (localDateTime != null) {
	    return Timestamp.valueOf(localDateTime);
	} else {
	    return null;
	}
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
	if (sqlTimestamp != null) {
	    return sqlTimestamp.toLocalDateTime();
	} else {
	    return null;
	}
    }

}
