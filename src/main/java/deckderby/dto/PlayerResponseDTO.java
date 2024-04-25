package deckderby.dto;

public class PlayerResponseDTO {
    private String username;
    private long userId;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "PlayerResponseDTO [username=" + username + ", userId=" + userId + "]";
    }

}