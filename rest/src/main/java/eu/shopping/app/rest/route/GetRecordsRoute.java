package eu.shopping.app.rest.route;

import eu.shopping.app.rest.converter.ShoppingRecordB2R;
import eu.shopping.app.rest.exception.RestStorageException;
import eu.shopping.app.rest.util.JsonSerializer;
import eu.shopping.app.usecase.api.ShoppingUseCaseFactory;
import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;
import eu.shopping.app.usecase.api.exception.BoundaryStorageException;
import spark.Request;
import spark.Response;

import java.util.List;

public class GetRecordsRoute extends RestRoute {
    private final JsonSerializer serializer;
    private final ShoppingUseCaseFactory factory;
    private final ShoppingRecordB2R converter;

    public GetRecordsRoute(JsonSerializer serializer, ShoppingUseCaseFactory factory, ShoppingRecordB2R converter) {
        this.serializer = serializer;
        this.factory = factory;
        this.converter = converter;
    }

    @Override
    void process(Request request, Response response) {
        List<BoundaryShoppingRecord> result = factory.getAllShoppingRecordsUseCase().run();
        String body = serializer.toJson(converter.convert(result));
        response.body(body);
    }

    @Override
    protected void handleException(Exception e) {
        if (e instanceof BoundaryStorageException)
            throw new RestStorageException(e);
    }
}