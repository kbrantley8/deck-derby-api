package deckderby.utils;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckDerbyResponse extends APIGatewayV2HTTPResponse {

    public DeckDerbyResponse() {
        super();
    }

    public DeckDerbyResponse(int statusCode, Map<String, String> headers, Map<String, List<String>> multiValueHeaders, List<String> cookies, String body, boolean isBase64Encoded) {
        super(statusCode, headers, multiValueHeaders, cookies, body, isBase64Encoded);
    }

    public APIGatewayV2HTTPResponse generateSuccessfulResponse(String body) {
        this.setStatusCode(200);
        this.setBody(body);

        return this;
    }

    public APIGatewayV2HTTPResponse generateErrorResponse(String message) {
        this.setStatusCode(400);
        Map<String, String> errorObj = new HashMap<>();
        errorObj.put("message", message);
        this.setBody(errorObj.toString());

        return this;
    }

    public APIGatewayV2HTTPResponse generateNotFoundErrorResponse(String message) {
        this.setStatusCode(404);
        Map<String, String> errorObj = new HashMap<>();
        errorObj.put("message", message);
        this.setBody(errorObj.toString());

        return this;
    }

}
