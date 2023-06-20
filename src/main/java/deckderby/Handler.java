package deckderby;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import deckderby.utils.ApplicationPropertiesManager;
import deckderby.utils.ApplicationRouteDirector;

import java.sql.SQLException;

public class Handler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse>{

    private static final Logger logger = LogManager.getLogger(Handler.class);
    private final String environment;

    public Handler() {
        environment = System.getenv("ENV");
    }

    public void initialize() {
        ApplicationPropertiesManager.loadProperties(environment);
    }

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent event, Context context)
    {
        initialize();

        logger.info(String.format("Incoming request with routeKey: [%s] and requestId: [%s]", event.getRouteKey(), event.getRequestContext().getRequestId()));

        APIGatewayV2HTTPResponse resp = null;
        try {
            resp = new ApplicationRouteDirector().handleRoute(event);
        } catch (SQLException | ClassNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
            return APIGatewayV2HTTPResponse.builder().withStatusCode(400).withBody(e.getMessage()).build();
        }

        if (resp != null) {
            logger.info(String.format("Response for requestId: [%s] -> %s", event.getRequestContext().getRequestId(), resp.toString()));
            return resp;
        } else {
            return APIGatewayV2HTTPResponse.builder().withStatusCode(400).withBody("Response is empty!").build();
        }

    }
}
