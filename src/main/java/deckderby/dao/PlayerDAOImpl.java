package deckderby.dao;

import deckderby.models.Player;
import deckderby.utils.ApplicationPropertiesManager;
import deckderby.utils.logger.ApplicationLogger;
import deckderby.utils.logger.LoggerContext;
import deckderby.utils.misc.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerDAOImpl implements PlayerDAO {
    private final ApplicationLogger logger = LoggerContext.getInstance().getLogger();

    private Connection connection;
    private final String tableName;

    public PlayerDAOImpl() throws ClassNotFoundException, SQLException {
        String url = ApplicationPropertiesManager.getProperty("postgres.config.host");
        String username = ApplicationPropertiesManager.getProperty("postgres.config.username");
        String password = ApplicationPropertiesManager.getProperty("postgres.config.password");
        String postgresqlClass = ApplicationPropertiesManager.getProperty("postgres.config.driver");

        try {
            Class.forName(postgresqlClass);
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(String.format("Postgres class for %s was not found!", postgresqlClass));
        } catch (SQLException e) {
            throw new SQLException(String.format("Postgres connection failed in %s! Double check your connection parameters to %s", getClass(), url));
        }
        this.tableName = "Player";
    }

    @Override
    public Player getPlayerByUsername(String username) throws SQLException {
        String criteria_col = "username";
        String sql_stmt = String.format(
                "SELECT * FROM %s WHERE %s=?",
                this.tableName,
                criteria_col);

        PreparedStatement ps = this.connection.prepareStatement(sql_stmt);

        ps.setFetchSize(1);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Player(
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("password_salt"),
                    rs.getInt("total_winnings"),
                    rs.getString("confirmation_code"),
                    rs.getDate("confirmation_date")
            );
        }

        rs.close();

        return null;

    }

    @Override
    public Player getPlayerByEmail(String email) throws SQLException {
        String criteria_col = "email";
        String sql_stmt = String.format(
                "SELECT * FROM %s WHERE %s=?",
                this.tableName,
                criteria_col);

        PreparedStatement ps = this.connection.prepareStatement(sql_stmt);

        ps.setFetchSize(1);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Player(
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getString("password_salt"),
                    rs.getInt("total_winnings"),
                    rs.getString("confirmation_code"),
                    rs.getDate("confirmation_date")
            );
        }

        rs.close();

        return null;

    }

    @Override
    public void save(Player player) throws SQLException {
        String sql_stmt = String.format(
                "INSERT INTO %s " +
                        "(username, email, password_hash, password_salt, total_winnings, confirmation_code, confirmation_date) " +
                        "VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?)",
                this.tableName);

        PreparedStatement ps = this.connection.prepareStatement(sql_stmt);

        ps.setString(1, player.getUsername());
        ps.setString(2, player.getEmail());
        ps.setString(3, player.getPassword_hash());
        ps.setString(4, player.getPassword_salt());
        ps.setInt(5, player.getTotal_winnings());
        ps.setString(6, player.getConfirmation_code());
        ps.setDate(7, player.getConfirmation_date());

        ps.executeUpdate();
    }

//    @Override
//    public Player login(String username, String password) throws SQLException {
//        String criteria_col = "username";
//        String sql_stmt = String.format(
//                "SELECT username, password_hash, password_salt FROM %s WHERE %s=?",
//                this.tableName,
//                criteria_col);
//
//        PreparedStatement ps = this.connection.prepareStatement(sql_stmt);
//
//        ps.setFetchSize(1);
//        ps.setString(1, username);
//
//        ResultSet rs = ps.executeQuery();
//
//        if (rs.next()) {
//            return new Player(
//                    rs.getString("username"),
//                    rs.getString("password_hash"),
//                    rs.getString("password_salt")
//            );
//
//        }
//
//        rs.close();
//
//        return null;
//    }

    public void update(String username, Player updatedPlayer) throws SQLException {
        Player existingPlayer = this.getPlayerByUsername(updatedPlayer.getUsername());
        List<String> updatedColumns = new ArrayList<>();
        List<Object> updatedValues = new ArrayList<>();

        if (existingPlayer != null) {
            if (!Objects.equals(updatedPlayer.getEmail(), existingPlayer.getEmail())) {
                updatedColumns.add("email = ?");
                updatedValues.add(updatedPlayer.getEmail());
            }
            if (!Objects.equals(updatedPlayer.getPassword_hash(), existingPlayer.getPassword_hash())) {
                updatedColumns.add("password_hash = ?");
                updatedValues.add(updatedPlayer.getPassword_hash());
            }
            if (!Objects.equals(updatedPlayer.getPassword_salt(), existingPlayer.getPassword_salt())) {
                updatedColumns.add("password_salt = ?");
                updatedValues.add(updatedPlayer.getPassword_salt());
            }
            if (!Objects.equals(updatedPlayer.getTotal_winnings(), existingPlayer.getTotal_winnings())) {
                updatedColumns.add("total_winnings = ?");
                updatedValues.add(updatedPlayer.getTotal_winnings());
            }
            if (!Objects.equals(updatedPlayer.getConfirmation_code(), existingPlayer.getConfirmation_code())) {
                updatedColumns.add("confirmation_code = ?");
                updatedValues.add(updatedPlayer.getConfirmation_code());
            }
            if (!Objects.equals(updatedPlayer.getConfirmation_date(), existingPlayer.getConfirmation_date())) {
                updatedColumns.add("confirmation_date = ?");
                updatedValues.add(updatedPlayer.getConfirmation_date());
            }
        }

        if (updatedColumns.size() != 0) {

            StringBuilder queryBuilder = new StringBuilder(String.format("UPDATE %s SET", this.tableName));
            String updateColumnsString = String.join(", ", updatedColumns);
            queryBuilder.append(" ").append(updateColumnsString).append(" WHERE username = ?");

            System.out.println(queryBuilder);

            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());

            for (int i = 0; i < updatedValues.size(); i++) {
                statement.setObject(i + 1, updatedValues.get(i));
            }

            statement.setString(updatedValues.size() + 1, username);

            statement.executeUpdate();

        }


    }

}
