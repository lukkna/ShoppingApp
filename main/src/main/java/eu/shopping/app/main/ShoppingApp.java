package eu.shopping.app.main;

import spark.ExceptionHandler;
import spark.Response;
import spark.Service;

import static eu.shopping.app.rest.util.RestConstants.CONTENT_TYPE_JSON;
import static spark.Service.ignite;

public class ShoppingApp {
    private ShoppingApp() {
    }

    public static ShoppingApp create() {
        return new ShoppingApp();
    }

    public void start() {
        Service service = ignite();
        service.port(4567);
        service.exception(RuntimeException.class, createExceptionHandler());
        RoutesBuilder.setService(service).attachRoutes();
        service.awaitInitialization();
    }

    private ExceptionHandler<RuntimeException> createExceptionHandler() {
        return (exception, request, response) -> addToResponse(exception, response);
    }

    private void addToResponse(RuntimeException exception, Response response) {
        response.type(CONTENT_TYPE_JSON);
        response.status(500);
        response.body(exception.getMessage());
    }
}