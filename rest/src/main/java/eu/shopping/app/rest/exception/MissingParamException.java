package eu.shopping.app.rest.exception;

public class MissingParamException extends RestException {
    public MissingParamException(String param) {
        super("Missing required " + param, 422);
    }
}