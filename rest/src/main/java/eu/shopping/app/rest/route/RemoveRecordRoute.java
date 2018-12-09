package eu.shopping.app.rest.route;

import eu.shopping.app.usecase.api.ShoppingUseCaseFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import static eu.shopping.app.rest.util.RestConstants.CONTENT_TYPE_JSON;

public class RemoveRecordRoute implements Route {
    private final ShoppingUseCaseFactory factory;

    public RemoveRecordRoute(ShoppingUseCaseFactory factory) {
        this.factory = factory;
    }

    @Override
    public Object handle(Request request, Response response) {
        String id = request.params("id");

        if (id == null) {
            response.body("Missing id param");
            response.status(422);
        } else {
            factory.removeShoppingRecordUseCase()
                    .run(Long.valueOf(id));

            response.status(200);
            response.body("OK");
            response.type(CONTENT_TYPE_JSON);
        }
        return response.body();
    }
}