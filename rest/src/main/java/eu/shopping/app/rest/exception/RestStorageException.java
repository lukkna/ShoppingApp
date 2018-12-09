package eu.shopping.app.rest.exception;

public class RestStorageException extends RestException {
    public RestStorageException(Exception e) {
        super(e, "Something wrong with storage", 500);
    }
}