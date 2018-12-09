package eu.shopping.app.rest.route;

import eu.shopping.app.rest.converter.ShoppingRecordR2B;
import eu.shopping.app.rest.entity.RestShoppingRecord;
import eu.shopping.app.rest.exception.RestStorageException;
import eu.shopping.app.rest.util.JsonSerializer;
import eu.shopping.app.usecase.api.ShoppingUseCaseFactory;
import eu.shopping.app.usecase.api.exception.BoundaryStorageException;
import spark.Request;
import spark.Response;

public class PostRecordsRoute extends RestRoute {
    private final JsonSerializer serializer;
    private final ShoppingUseCaseFactory factory;
    private final ShoppingRecordR2B converter;

    public PostRecordsRoute(JsonSerializer serializer, ShoppingUseCaseFactory factory, ShoppingRecordR2B converter) {
        this.serializer = serializer;
        this.factory = factory;
        this.converter = converter;
    }

    @Override
    void process(Request request, Response response) {
        RestShoppingRecord record = serializer.toObject(getBody(request), RestShoppingRecord.class);
        factory.addShoppingRecordUseCase()
                .run(converter.convert(record).orElse(null));
    }

    @Override
    protected void handleException(Exception e) {
        if (e instanceof BoundaryStorageException)
            throw new RestStorageException(e);
    }
}