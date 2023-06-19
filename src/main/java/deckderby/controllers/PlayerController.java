package deckderby.controllers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import deckderby.models.Player;
import deckderby.models.requests.PlayerRequestBody;
import deckderby.services.PlayerServiceImpl;
import deckderby.utils.DeckDerbyResponse;
import deckderby.utils.logger.ApplicationLogger;
import deckderby.utils.logger.LoggerContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

public class PlayerController implements Controller {
    private final ApplicationLogger logger = LoggerContext.getInstance().getLogger();
    PlayerServiceImpl playerServiceImpl;

    public PlayerController() throws SQLException, ClassNotFoundException {
        this.playerServiceImpl = new PlayerServiceImpl();
    }

    public APIGatewayV2HTTPResponse process(String httpMethod, String httpPath, APIGatewayV2HTTPEvent event) throws SQLException {
        switch (httpMethod) {
            case ("GET"):
                logger.info(String.format("Path and method found. Beginning processing of HTTPMethod [%s] for path [%s]...", httpMethod, httpPath));
                return this.process_GET(event);
            case ("POST"):
                logger.info(String.format("Path and method found. Beginning processing of HTTPMethod [%s] for path [%s]...", httpMethod, httpPath));
                return this.process_POST(event, httpPath.split("/"));
            case ("PUT"):
                logger.info(String.format("Path and method found. Beginning processing of HTTPMethod [%s] for path [%s]...", httpMethod, httpPath));
                return this.process_PUT(event, httpPath.split("/"));
            default:
                logger.info("NO METHOD MATCHED");
                return new DeckDerbyResponse().generateErrorResponse(String.format("The method [%s] is not supported for [%s]", httpMethod, httpPath));
        }

    }

    public APIGatewayV2HTTPResponse process_GET(APIGatewayV2HTTPEvent event) throws SQLException {
        Map<String, String> pathParams = event.getPathParameters();
        String username = pathParams.get("username");

        if (username == null || username.isBlank()) {
            return new DeckDerbyResponse().generateErrorResponse("Username cannot be null or blank!");
        }

        Player player = this.getPlayerByUsername(username);

        if (player == null) {
            return new DeckDerbyResponse().generateNotFoundErrorResponse(String.format("Player with username [%s] was not found", username));
        }

        logger.info("Player was found successfully...");
        return new DeckDerbyResponse().generateSuccessfulResponse(player.toString());
    }

    public APIGatewayV2HTTPResponse process_POST(APIGatewayV2HTTPEvent event, String[] direction) {
        String path = event.getRawPath();
        String[] pathPieces = path.split("/");

        String body = event.getBody();
        PlayerRequestBody playerRequestBody;
        try {
            playerRequestBody = convertBody(body);
        } catch (JsonProcessingException e) {
            return new DeckDerbyResponse().generateErrorResponse("Body is malformed for Player! Must be of the form: username, email, password.");
        }

        String username = playerRequestBody.getUsername();
        String email = playerRequestBody.getEmail();
        String password = playerRequestBody.getPassword();
        String newPassword = playerRequestBody.getNew_password();
        String oldPassword = playerRequestBody.getOld_password();

        Player player;
        try {
            switch (pathPieces[2]) {
                case ("login"):
                    logger.info(String.format("Logging in user [%s]...", username));
                    player = this.loginPlayer(username, password);

                    logger.info("Player was logged in successfully...");
                    return new DeckDerbyResponse().generateSuccessfulResponse(String.format("Player [%s] successfully logged in!", player.getUsername()));
                case ("player"):
                    if (Objects.equals(direction[4], "password")) {
                        username = event.getPathParameters().get("username");
                        logger.info(String.format("Updating password process started for [%s]...", username));
                        this.updatePassword(username, newPassword, oldPassword);

                        logger.info("Password updated successfully...");
                        return new DeckDerbyResponse().generateSuccessfulResponse(String.format("Player [%s] successfully updated password!", username));
                    } else {
                        return new DeckDerbyResponse().generateErrorResponse("The path doesn't exist!");
                    }
                default:
                    player = this.savePlayer(username, email, password);

                    logger.info("Player was created successfully...");
                    return new DeckDerbyResponse().generateSuccessfulResponse(String.format("Player with username [%s] was successfully created!", player.getUsername()));
            }
        } catch (Exception e) {
            return new DeckDerbyResponse().generateErrorResponse(e.getMessage());
        }
    }

    public APIGatewayV2HTTPResponse process_PUT(APIGatewayV2HTTPEvent event, String[] direction) {
        String body = event.getBody();
        PlayerRequestBody playerRequestBody;
        try {
            playerRequestBody = convertBody(body);
        } catch (JsonProcessingException e) {
            return new DeckDerbyResponse().generateErrorResponse("Body is malformed for Player! Must be of the form: username, email, password.");
        }

        Map<String, String> pathParams = event.getPathParameters();
        String username = pathParams.get("username");
        String email = playerRequestBody.getEmail();
        Integer total_winnings = playerRequestBody.getTotal_winnings();
        String confirmation_code = playerRequestBody.getConfirmation_code();
        String confirmation_date = playerRequestBody.getConfirmation_date();

        try {
            switch (direction[4]) {
                case "email":
                    this.updateEmail(username, email);
                    return new DeckDerbyResponse().generateSuccessfulResponse(String.format("Player with username [%s] updated email successfully!", username));
                case "total_winnings":
                    this.updateTotalWinnings(username, total_winnings);
                    return new DeckDerbyResponse().generateSuccessfulResponse(String.format("Player with username [%s] updated total_winnings successfully!", username));
                case "confirmation_code":
                    this.updateConfirmationCode(username, confirmation_code);
                    return new DeckDerbyResponse().generateSuccessfulResponse(String.format("Player with username [%s] updated confirmation_code successfully!", username));
                case "confirmation_date":
                    this.updateConfirmationDate(username, Date.valueOf(confirmation_date));
                    return new DeckDerbyResponse().generateSuccessfulResponse(String.format("Player with username [%s] updated confirmation_date successfully!", username));
                default:
                    return new DeckDerbyResponse().generateErrorResponse(String.format("Path [%s] did not match any routes!", direction[4]));
            }
        } catch (SQLException e) {
            return new DeckDerbyResponse().generateErrorResponse(e.getMessage());
        }
    }

    private Player getPlayerByUsername(String username) throws SQLException {
        return this.playerServiceImpl.getPlayerByUsername(username);
    }

    private Player getPlayerByEmail(String email) throws SQLException {
        return this.playerServiceImpl.getPlayerByEmail(email);
    }

    private Player savePlayer(String username, String email, String password) throws SQLException {
        return this.playerServiceImpl.savePlayer(username, email, password);
    }

    private Player loginPlayer(String username, String password) throws Exception {
        return this.playerServiceImpl.loginPlayer(username, password);
    }

    private void updateEmail(String username, String email) throws SQLException {
        this.playerServiceImpl.updateEmail(username, email);
    }

    private void updateTotalWinnings(String username, Integer newTotalWinnings) throws SQLException {
        this.playerServiceImpl.updateTotalWinnings(username, newTotalWinnings);
    }

    private void updateConfirmationCode(String username, String newConfirmationCode) throws SQLException {
        this.playerServiceImpl.updateConfirmationCode(username, newConfirmationCode);
    }

    private void updateConfirmationDate(String username, Date newConfirmationDate) throws SQLException {
        this.playerServiceImpl.updateConfirmationDate(username, newConfirmationDate);
    }

    private void updatePassword(String username, String oldPassword, String newPassword) throws SQLException {
        if (username == null) {
            throw new IllegalArgumentException("The username must be provided!");
        }
        if (oldPassword == null || newPassword == null) {
            throw new IllegalArgumentException("The current password and the new password are required!");
        }
        this.playerServiceImpl.updatePassword(username, oldPassword, newPassword);
    }

    private PlayerRequestBody convertBody(String body) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(body, PlayerRequestBody.class);
    }
}
