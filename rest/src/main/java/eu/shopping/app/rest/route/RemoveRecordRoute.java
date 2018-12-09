package eu.shopping.app.rest.route;

import eu.shopping.app.rest.exception.RestStorageException;
import eu.shopping.app.usecase.api.ShoppingUseCaseFactory;
import eu.shopping.app.usecase.api.exception.BoundaryStorageException;
import spark.Request;
import spark.Response;

public class RemoveRecordRoute extends RestRoute {
    private final ShoppingUseCaseFactory factory;

    public RemoveRecordRoute(ShoppingUseCaseFactory factory) {
        this.factory = factory;
    }

    @Override
    void process(Request request, Response response) {
        long id = getLongPathParam(request, "id");
        factory.removeShoppingRecordUseCase()
                .run(id);
    }

    @Override
    protected void handleException(Exception e) {
        if (e instanceof BoundaryStorageException)
            throw new RestStorageException(e);
    }
}