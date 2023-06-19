package deckderby;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import deckderby.controllers.PlayerController;
import deckderby.models.Player;
import deckderby.utils.ApplicationPropertiesManager;
import deckderby.utils.ApplicationRouteDirector;
import deckderby.utils.logger.ApplicationLogger;
import deckderby.utils.logger.LoggerContext;

import java.sql.SQLException;

public class Handler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse>{

    private ApplicationLogger logger;
    private final String environment;

    public Handler() {
        logger = null;
        environment = System.getenv("ENV");
    }

    public void initialize(Context context) {
        if (logger == null) {
            LoggerContext.initialize(context);
            LoggerContext lambdaContext = LoggerContext.getInstance();
            logger = lambdaContext.getLogger();
        }
        ApplicationPropertiesManager.loadProperties(environment);
    }

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent event, Context context)
    {
        initialize(context);

        logger.info(String.format("Incoming request with routeKey: [%s] and requestId: [%s]", event.getRouteKey(), event.getRequestContext().getRequestId()));

        APIGatewayV2HTTPResponse resp = null;
        try {
            resp = new ApplicationRouteDirector().handleRoute(event);
        } catch (SQLException | ClassNotFoundException | JsonProcessingException e) {
            e.printStackTrace();
        }

        logger.info(String.format("Response for requestId: [%s] \t %s", event.getRequestContext().getRequestId(),resp.toString()));

        return resp;
    }
}
