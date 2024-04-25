package deckderby.core;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password_hash;

    @Column(nullable = false)
    private String password_salt;

    @Column()
    private Integer total_winnings;

    @Column()
    private String confirmation_code;

    @Column()
    private Date confirmation_date;

    public Player() { };

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

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_salt() {
        return password_salt;
    }

    public void setPassword_salt(String password_salt) {
        this.password_salt = password_salt;
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

    public Date getConfirmation_date() {
        return confirmation_date;
    }

    public void setConfirmation_date(Date confirmation_date) {
        this.confirmation_date = confirmation_date;
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", password_salt='" + password_salt + '\'' +
                ", total_winnings=" + total_winnings +
                ", confirmation_code='" + confirmation_code + '\'' +
                ", confirmation_date=" + confirmation_date +
                '}';
    }
}
