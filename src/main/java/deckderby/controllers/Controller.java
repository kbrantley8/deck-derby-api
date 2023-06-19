package deckderby.controllers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;

public interface Controller {
    public APIGatewayV2HTTPResponse process(String httpMethod, String httpPath, APIGatewayV2HTTPEvent event) throws SQLException;
}
