package deckderby.services;

import deckderby.dao.PlayerDAOImpl;
import deckderby.models.Player;
import deckderby.utils.misc.PasswordUtil;
import org.postgresql.util.PSQLException;

import java.sql.Date;
import java.sql.SQLException;

public class PlayerServiceImpl implements PlayerService {

    private final PlayerDAOImpl playerDAOImpl;
    private final Integer DEFAULT_TOTAL_WINNINGS = 100;

    public PlayerServiceImpl() throws SQLException, ClassNotFoundException {
        this.playerDAOImpl = new PlayerDAOImpl();
    }

    public Player getPlayerByUsername(String username) throws SQLException {
        return this.playerDAOImpl.getPlayerByUsername(username);
    }

    public Player getPlayerByEmail(String email) throws SQLException {
        return this.playerDAOImpl.getPlayerByEmail(email);
    }

    public Player savePlayer(String username, String email, String password) throws SQLException {
        String salt = PasswordUtil.createSalt();
        String hashedPW = PasswordUtil.hashPassword(password, salt);
        Player player = new Player(username, email, hashedPW, salt, DEFAULT_TOTAL_WINNINGS);

        this.playerDAOImpl.save(player);

        return player;
    }

    public Player loginPlayer(String username, String password) throws Exception {
        Player playerInfo = this.playerDAOImpl.getPlayerByUsername(username);

        boolean isPasswordCorrect = PasswordUtil.verifyPassword(password, playerInfo.getPassword_hash());

        if (isPasswordCorrect) {
            return playerInfo;
        } else {
            throw new Exception("Password is incorrect! Please try again.");
        }
    }

    public void updateEmail(String username, String email) throws SQLException {
        Player player = this.playerDAOImpl.getPlayerByUsername(username);

        if (player == null) {
            throw new SQLException(String.format("Player with username [%s] does not exist!", username));
        }

        player.setEmail(email);

        this.playerDAOImpl.update(username, player);
    }

    public void updateTotalWinnings(String username, Integer newTotalWinnings) throws SQLException {
        Player player = this.playerDAOImpl.getPlayerByUsername(username);

        if (player == null) {
            throw new SQLException(String.format("Player with username [%s] does not exist!", username));
        }

        player.setTotal_winnings(newTotalWinnings);

        this.playerDAOImpl.update(username, player);
    }

    public void updateConfirmationCode(String username, String newConfirmationCode) throws SQLException {
        Player player = this.playerDAOImpl.getPlayerByUsername(username);

        if (player == null) {
            throw new SQLException(String.format("Player with username [%s] does not exist!", username));
        }

        player.setConfirmation_code(newConfirmationCode);

        this.playerDAOImpl.update(username, player);
    }

    public void updateConfirmationDate(String username, Date newConfirmationDate) throws SQLException {
        Player player = this.playerDAOImpl.getPlayerByUsername(username);

        if (player == null) {
            throw new SQLException(String.format("Player with username [%s] does not exist!", username));
        }

        player.setConfirmation_date(newConfirmationDate);

        this.playerDAOImpl.update(username, player);
    }

    public void updatePassword(String username, String newPassword, String oldPassword) throws SQLException {
        Player player = this.playerDAOImpl.getPlayerByUsername(username);

        if (player == null) {
            throw new SQLException(String.format("Player with username [%s] does not exist!", username));
        }

        if (!PasswordUtil.verifyPassword(oldPassword, player.getPassword_hash())) {
            throw new IllegalArgumentException(String.format("Incorrect password provided for [%s]!", player.getUsername()));
        }

        String salt = PasswordUtil.createSalt();
        String hashedPW = PasswordUtil.hashPassword(newPassword, salt);

        player.setPassword_salt(salt);
        player.setPassword_hash(hashedPW);

        this.playerDAOImpl.update(username, player);
    }


}
