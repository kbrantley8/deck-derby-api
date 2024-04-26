package deckderby.model;

public class PlayerErrorResponse implements IPlayerResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PlayerErrorResponse [message=" + message + "]";
    }
}
