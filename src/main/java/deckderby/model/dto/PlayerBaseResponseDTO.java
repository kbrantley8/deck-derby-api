package deckderby.model.dto;

import deckderby.model.IPlayerResponse;

public class PlayerBaseResponseDTO implements IPlayerResponse {
    private long userId;
    private String username;
    private Integer total_winnings;
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getTotal_winnings() {
        return total_winnings;
    }
    public void setTotal_winnings(Integer total_winnings) {
        this.total_winnings = total_winnings;
    }
    @Override
    public String toString() {
        return "PlayerBaseResponseDTO [userId=" + userId + ", username=" + username + ", total_winnings="
                + total_winnings + "]";
    }

}
