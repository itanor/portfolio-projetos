package teste;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

   @Override
   public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

    Instant instant = ofEpochMilli(parser.getLongValue());
    return ofInstant(instant, ZoneId.of("America/Sao_Paulo"));
   }
}

