package eu.shopping.app.rest.exception;

public class RestException extends RuntimeException {
    private final int code;

    public RestException(String message, int code) {
        super(message);
        this.code = code;
    }

    public RestException(Exception e, String message, int code) {
        super(message, e);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}