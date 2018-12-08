package eu.shopping.app.rest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class JsonSerializer {
    private final ObjectMapper mapper;

    public JsonSerializer() {
        mapper = setUpMapper();
    }

    public Optional<String> toJson(Object originalObject) {
        try {
            return Optional.of(mapper.writeValueAsString(originalObject));
        } catch (JsonProcessingException e) {
            //todo handle
            return Optional.empty();
        }
    }

    public <T> Optional<T> toObject(String body, Class<T> clazz) {
        try {
            return Optional.of(mapper.readValue(body, clazz));
        } catch (IOException e) {
            //todo handle
            return Optional.empty();
        }
    }

    private ObjectMapper setUpMapper() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        om.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setSerializationInclusion(NON_NULL);
        return om;
    }
}