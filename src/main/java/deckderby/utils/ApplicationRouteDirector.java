package deckderby.utils;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import deckderby.Handler;
import deckderby.controllers.PlayerController;
import deckderby.models.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ApplicationRouteDirector {

    private static final Logger logger = LogManager.getLogger(ApplicationRouteDirector.class);
    PlayerController playerController;
    String apiPrefix;

    public ApplicationRouteDirector() throws SQLException, ClassNotFoundException {
        this.playerController = new PlayerController();
        this.apiPrefix = ApplicationPropertiesManager.getProperty("api.route.prefix");
    }

    public APIGatewayV2HTTPResponse handleRoute(APIGatewayV2HTTPEvent event) throws SQLException, JsonProcessingException {
        String httpMethod = event.getRequestContext().getHttp().getMethod();
        String httpPath = event.getRequestContext().getHttp().getPath();
        String[] direction = httpPath.split("/");

        logger.info(String.format("HTTPMethod: [%s], Full path: [%s], Path prefix: [%s], Path Specific: [%s]", httpMethod, httpPath, this.apiPrefix, "/"+direction[2]));

        switch (direction[2]) {
            case ("player"):
            case ("login"):
                logger.info("Running PlayerController...");
                return this.playerController.process(httpMethod, httpPath, event);
            default:
                logger.info("Incoming request path did not match any endpoints!");
                return APIGatewayV2HTTPResponse.builder()
                        .withStatusCode(404)
                        .withBody("{ \"error\": \"The provided path [" + httpPath + "] does not exist!\" }")
                        .build();
        }
    }

}
