package com.planner.trip.json.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateDeserializer extends StdDeserializer<LocalDateTime> {
    public DateDeserializer() {
        this(null);
    }
    public DateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser,
                                     DeserializationContext deserializationContext)
                                    throws IOException, JsonProcessingException {
        return LocalDateTime.parse(jsonParser.getText(), DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
