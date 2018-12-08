package eu.shopping.app.rest.route;

import eu.shopping.app.rest.converter.ShoppingRecordB2R;
import eu.shopping.app.rest.util.JsonSerializer;
import eu.shopping.app.usecase.api.ShoppingUseCaseFactory;
import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;
import java.util.Optional;

import static eu.shopping.app.rest.util.RestConstants.CONTENT_TYPE_JSON;

public class GetRecordsRoute implements Route {
    private final JsonSerializer serializer;
    private final ShoppingUseCaseFactory factory;
    private final ShoppingRecordB2R converter;

    public GetRecordsRoute(JsonSerializer serializer, ShoppingUseCaseFactory factory, ShoppingRecordB2R converter) {
        this.serializer = serializer;
        this.factory = factory;
        this.converter = converter;
    }

    @Override
    public Object handle(Request request, Response response) {
        List<BoundaryShoppingRecord> result = factory.getAllShoppingRecordsUseCase().run();
        Optional<String> body = serializer.toJson(converter.convert(result));

        if (body.isPresent()) {
            response.body(body.get());
            response.status(200);
            response.type(CONTENT_TYPE_JSON);
            return response.body();
        } else {
            return response.raw();//todo properly handle
        }
    }
}