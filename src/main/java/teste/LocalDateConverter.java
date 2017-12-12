package teste;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.sql.Date;
import java.sql.Timestamp;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDateTime, Timestamp> {
      
  @Override
  public Timestamp convertToDatabaseColumn(LocalDateTime locDate) {
    return (locDate == null ? null : Timestamp.valueOf(locDate));
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Timestamp sqlDate) {
    return (sqlDate == null ? null : sqlDate.toLocalDateTime());
  }
}

