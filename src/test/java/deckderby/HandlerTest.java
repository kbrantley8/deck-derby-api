package deckderby;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.amazonaws.services.lambda.runtime.tests.annotations.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HandlerTest {

    private Handler handler;

    @Mock
    Context contextMock;

    @BeforeEach
    public void setUp() {
        handler = new Handler();
    }

    @AfterEach
    public void tearDown() {
        handler = null;
    }

    @Disabled
    @ParameterizedTest
    @Event(value = "player-getByUsername-event.json", type = APIGatewayV2HTTPEvent.class)
    public void testGetPlayerByUsername(APIGatewayV2HTTPEvent event) {
        APIGatewayV2HTTPResponse response = handler.handleRequest(event, contextMock);
    }

    @Disabled
    @ParameterizedTest
    @Event(value = "player-save-event.json", type = APIGatewayV2HTTPEvent.class)
    public void testSavePlayer(APIGatewayV2HTTPEvent event) {
        APIGatewayV2HTTPResponse response = handler.handleRequest(event, contextMock);
    }

    @Disabled
    @ParameterizedTest
    @Event(value = "player-login-event.json", type = APIGatewayV2HTTPEvent.class)
    public void testLoginPlayer(APIGatewayV2HTTPEvent event) {
        APIGatewayV2HTTPResponse response = handler.handleRequest(event, contextMock);
    }

    @Disabled
    @ParameterizedTest
    @Event(value = "player-updatePassword-event.json", type = APIGatewayV2HTTPEvent.class)
    public void testUpdatePassword(APIGatewayV2HTTPEvent event) {
        APIGatewayV2HTTPResponse response = handler.handleRequest(event, contextMock);
    }

    @Disabled
    @ParameterizedTest
    @Event(value = "player-edit-event.json", type = APIGatewayV2HTTPEvent.class)
    public void testEditPlayer(APIGatewayV2HTTPEvent event) {
        APIGatewayV2HTTPResponse response = handler.handleRequest(event, contextMock);
    }

    @Disabled
    @ParameterizedTest
    @Event(value = "player-updateEmail-event.json", type = APIGatewayV2HTTPEvent.class)
    public void testUpdateEmail(APIGatewayV2HTTPEvent event) {
        APIGatewayV2HTTPResponse response = handler.handleRequest(event, contextMock);
    }

    @Disabled
    @ParameterizedTest
    @Event(value = "player-updateTotalWinnings-event.json", type = APIGatewayV2HTTPEvent.class)
    public void testUpdateTotalWinnings(APIGatewayV2HTTPEvent event) {
        APIGatewayV2HTTPResponse response = handler.handleRequest(event, contextMock);
    }

    @Disabled
    @ParameterizedTest
    @Event(value = "player-updateConfirmationCode-event.json", type = APIGatewayV2HTTPEvent.class)
    public void testUpdateConfirmationCode(APIGatewayV2HTTPEvent event) {
        APIGatewayV2HTTPResponse response = handler.handleRequest(event, contextMock);
    }

    @Disabled
    @ParameterizedTest
    @Event(value = "player-updateConfirmationDate-event.json", type = APIGatewayV2HTTPEvent.class)
    public void testUpdateConfirmationDate(APIGatewayV2HTTPEvent event) {
        APIGatewayV2HTTPResponse response = handler.handleRequest(event, contextMock);
    }

}
