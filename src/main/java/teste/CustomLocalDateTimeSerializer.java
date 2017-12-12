package teste;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

   @Override
   public void serialize(LocalDateTime localDate, JsonGenerator gen, SerializerProvider serializers)
       throws IOException, JsonProcessingException {

    ZonedDateTime zoneDateTime = localDate.atZone(ZoneId.of("America/Sao_Paulo"));
    gen.writeNumber(zoneDateTime.toInstant().toEpochMilli());
   }
}
