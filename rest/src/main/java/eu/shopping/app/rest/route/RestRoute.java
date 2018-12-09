package eu.shopping.app.rest.route;

import eu.shopping.app.rest.exception.MissingParamException;
import spark.Request;
import spark.Response;
import spark.Route;

import static eu.shopping.app.rest.util.RestConstants.CONTENT_TYPE_JSON;
import static java.util.Objects.isNull;

public abstract class RestRoute implements Route {
    @Override
    public Object handle(Request request, Response response) {
        response.status(200);
        response.type(CONTENT_TYPE_JSON);

        try {
            process(request, response);
        } catch (Exception e) {
            handleException(e);
        }

        return isEmpty(response.body()) ? response.raw() : response.body();
    }

    private boolean isEmpty(String string) {
        return isNull(string) || string.isEmpty();
    }

    long getLongPathParam(Request request, String param) {
        String value = request.params(param);
        if (isEmpty(value))
            throw new MissingParamException(param + " in path");
        return Long.valueOf(value);
    }

    String getBody(Request request) {
        String body = request.body();
        if (isEmpty(body))
            throw new MissingParamException("shopping record model in body");
        return body;
    }

    protected void handleException(Exception e) {
    }

    abstract void process(Request request, Response response);
}