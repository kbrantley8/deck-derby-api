package deckderby.services;

import deckderby.models.Player;

import java.sql.Date;
import java.sql.SQLException;

public interface PlayerService {
    public Player getPlayerByUsername(String username) throws SQLException;

    public Player getPlayerByEmail(String email) throws SQLException;

    public Player savePlayer(String username, String email, String password) throws SQLException;

    public Player loginPlayer(String username, String password) throws Exception;

    public void updateEmail(String username, String email) throws SQLException;

    public void updateTotalWinnings(String username, Integer newTotalWinnings) throws SQLException;

    public void updateConfirmationCode(String username, String newConfirmationCode) throws SQLException;

    public void updateConfirmationDate(String username, Date newConfirmationDate) throws SQLException;

    public void updatePassword(String username, String newPassword, String oldPassword) throws SQLException;
}
