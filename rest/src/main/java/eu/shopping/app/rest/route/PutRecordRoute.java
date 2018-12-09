package eu.shopping.app.rest.route;

import eu.shopping.app.rest.converter.ShoppingRecordR2B;
import eu.shopping.app.rest.entity.RestShoppingRecord;
import eu.shopping.app.rest.util.JsonSerializer;
import eu.shopping.app.usecase.api.ShoppingUseCaseFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Optional;

import static eu.shopping.app.rest.util.RestConstants.CONTENT_TYPE_JSON;

public class PutRecordRoute implements Route {
    private final JsonSerializer serializer;
    private final ShoppingUseCaseFactory factory;
    private final ShoppingRecordR2B converter;

    public PutRecordRoute(JsonSerializer serializer, ShoppingUseCaseFactory factory, ShoppingRecordR2B converter) {
        this.serializer = serializer;
        this.factory = factory;
        this.converter = converter;
    }

    @Override
    public Object handle(Request request, Response response) {
        Optional<RestShoppingRecord> record = serializer.toObject(request.body(), RestShoppingRecord.class);

        if (record.isPresent()) {
            factory.updateShoppingRecordUseCase()
                    .run(converter.convert(record.get()).orElse(null));
            response.status(200);
            response.body(serializer.toJson(record.get()).orElse(null));
            response.type(CONTENT_TYPE_JSON);
            return response.body();
        } else {
            //todo handle
            return response.raw();
        }
    }
}