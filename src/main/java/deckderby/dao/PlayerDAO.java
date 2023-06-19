package deckderby.dao;

import deckderby.models.Player;

import java.sql.Date;
import java.sql.SQLException;

public interface PlayerDAO {

    public Player getPlayerByUsername(String username) throws SQLException;

    public Player getPlayerByEmail(String email) throws SQLException;

    public void save(Player player) throws SQLException;

    public void update(String username, Player player) throws SQLException;

}
