package eu.shopping.app.main;

import eu.shopping.app.rest.exception.RestException;
import spark.ExceptionHandler;
import spark.Response;
import spark.Service;

import static eu.shopping.app.main.CorsHeadersProvider.CORS_HEADERS;
import static eu.shopping.app.rest.util.RestConstants.CONTENT_TYPE_JSON;
import static java.lang.String.format;
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
        service.after((request, response) -> CORS_HEADERS.forEach(response::header));
        service.awaitInitialization();
    }

    private ExceptionHandler<RuntimeException> createExceptionHandler() {
        return (exception, request, response) -> addToResponse(exception, response);
    }

    private void addToResponse(RuntimeException exception, Response response) {
        if (exception instanceof RestException) {
            RestException e = (RestException) exception;
            updateResponse(response, e.getCode(), e.getMessage());
        } else
            updateResponse(response, 50, exception.getMessage());
    }

    private void updateResponse(Response response, int status, String message) {
        response.type(CONTENT_TYPE_JSON);
        response.status(500);
        response.body(format("{\"status\" : %s, \"message\" : \"%s\"}", status, message));
    }
}