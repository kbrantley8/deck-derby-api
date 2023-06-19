package deckderby.models.requests;

public class PlayerRequestBody {
    private String username;
    private String email;
    private String password;
    private Integer total_winnings;
    private String confirmation_code;
    private String confirmation_date;
    private String old_password;
    private String new_password;

    public PlayerRequestBody(String username, String email, String password, Integer total_winnings, String confirmation_code, String confirmation_date, String old_password, String new_password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.total_winnings = total_winnings;
        this.confirmation_code = confirmation_code;
        this.confirmation_date = confirmation_date;
        this.old_password = old_password;
        this.new_password = new_password;
    }

    public PlayerRequestBody() { };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTotal_winnings() {
        return total_winnings;
    }

    public void setTotal_winnings(Integer total_winnings) {
        this.total_winnings = total_winnings;
    }

    public String getConfirmation_code() {
        return confirmation_code;
    }

    public void setConfirmation_code(String confirmation_code) {
        this.confirmation_code = confirmation_code;
    }

    public String getConfirmation_date() {
        return confirmation_date;
    }

    public void setConfirmation_date(String confirmation_date) {
        this.confirmation_date = confirmation_date;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
