package eu.shopping.app.usecase.implementation.util;

import eu.shopping.app.gateway.api.exception.StorageException;
import eu.shopping.app.usecase.api.exception.BoundaryStorageException;

public class StorageExceptionRethrower {
    private StorageExceptionRethrower() {
    }

    public static <T> T run(Task<T> task) {
        try {
            return task.execute();
        } catch (StorageException e) {
            throw new BoundaryStorageException(e);
        }
    }

    public static void run(VoidTask task) {
        try {
            task.execute();
        } catch (StorageException e) {
            throw new BoundaryStorageException(e);
        }
    }

    public interface Task<T> {
        T execute();
    }

    public interface VoidTask {
        void execute();
    }
}