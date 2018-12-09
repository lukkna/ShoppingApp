package eu.shopping.app.rest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import eu.shopping.app.rest.exception.JsonParsingException;

import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class JsonSerializer {
    private final ObjectMapper mapper;

    public JsonSerializer() {
        mapper = setUpMapper();
    }

    public String toJson(Object originalObject) {
        try {
            return mapper.writeValueAsString(originalObject);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException(e);
        }
    }

    public <T> T toObject(String body, Class<T> clazz) {
        try {
            return mapper.readValue(body, clazz);
        } catch (IOException e) {
            throw new JsonParsingException(e);
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