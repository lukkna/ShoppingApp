package eu.shopping.app.rest.route;

import spark.Request;
import spark.Response;
import spark.Route;

public class OptionsRoute implements Route {
    @Override
    public Object handle(Request request, Response response) {
        response.status(200);
        return "";
    }
}