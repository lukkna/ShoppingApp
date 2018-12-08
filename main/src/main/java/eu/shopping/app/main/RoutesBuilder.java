package eu.shopping.app.main;

import eu.shopping.app.main.builder.ShoppingUseCaseFactoryBuilder;
import eu.shopping.app.rest.converter.ShoppingRecordB2R;
import eu.shopping.app.rest.converter.ShoppingRecordR2B;
import eu.shopping.app.rest.route.GetRecordsRoute;
import eu.shopping.app.rest.route.PostRecordsRoute;
import eu.shopping.app.rest.util.JsonSerializer;
import eu.shopping.app.usecase.api.ShoppingUseCaseFactory;
import spark.Service;

import static eu.shopping.app.rest.util.RestConstants.SHOPPING_RECORDS;

public class RoutesBuilder {
    private final Service service;
    private final JsonSerializer serializer;
    private final ShoppingUseCaseFactory factory;

    private RoutesBuilder(Service service) {
        this.service = service;
        serializer = new JsonSerializer();
        factory = ShoppingUseCaseFactoryBuilder.create().build();
    }

    public static RoutesBuilder setService(Service service) {
        return new RoutesBuilder(service);
    }

    public void attachRoutes() {
        service.path(SHOPPING_RECORDS, () -> {
            service.post("", new PostRecordsRoute(serializer, factory, new ShoppingRecordR2B()));
            service.get("", new GetRecordsRoute(serializer, factory, new ShoppingRecordB2R()));
        });
    }
}