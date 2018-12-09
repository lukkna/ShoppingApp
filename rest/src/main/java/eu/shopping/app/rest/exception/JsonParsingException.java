package eu.shopping.app.rest.exception;

public class JsonParsingException extends RestException {
    public JsonParsingException(Exception e) {
        super(e, "Failed to serialize/deserialize", 500);
    }
}