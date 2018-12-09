package eu.shopping.app.usecase.api.exception;

public class BoundaryStorageException extends RuntimeException {
    public BoundaryStorageException(Throwable cause) {
        super(cause);
    }
}