package deckderby.model.dto;

import jakarta.validation.constraints.*;

public class PlayerLoginRequestDTO {
    @NotBlank(message="username cannot be blank")
    private String username;

    @NotBlank(message="email cannot be blank")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PlayerLoginRequestDTO [username=" + username + "]";
    }
}
