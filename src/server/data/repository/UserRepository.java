package server.data.repository;

import db.ConnectionPool;
import server.data.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserRepository {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public UserRepository() throws SQLException {
    }

    @Override
    protected void finalize() throws IOException, SQLException {
    }

    public User save(User user) throws SQLException {
        insertUser(user);
        return user;
    }

    public User findUserByLogin(String login) throws SQLException {
        String query = "SELECT * FROM users WHERE login = ?";
        try (Connection connection = connectionPool.connectToDataBase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(UUID.fromString(resultSet.getString("id")));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setName(resultSet.getString("name"));
                    user.setLastname(resultSet.getString("lastname"));
                    user.setRole_id(UUID.fromString(resultSet.getString("role_id")));
                    user.setBlocked(resultSet.getBoolean("is_blocked"));
                    return user;
                } else {
                    System.out.println("No user found with login: " + login);
                }
            }
        }
        return null; // Return null if no matching record is found
    }


    public User findUserById(UUID id) throws SQLException {
        User user = null;

        Connection connection = connectionPool.connectToDataBase();

        String query = "SELECT * FROM users WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            user = new User();
            user.setId(UUID.fromString(resultSet.getString("id")));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setLastname(resultSet.getString("lastname"));
            user.setRole_id(UUID.fromString(resultSet.getString("role_id")));
            user.setBlocked(resultSet.getBoolean("is_blocked"));
        }
        else {
            throw new SQLException("User not found");
        }
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }

        return user;
    }

    public boolean isUserPresent(String login) throws SQLException {
        String query = "SELECT EXISTS (SELECT 1 FROM users WHERE login = ?)";

        try (Connection connection = connectionPool.connectToDataBase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
                return false;
            }
        }
    }


    public void delete(String login) throws SQLException {
        String query = "DELETE FROM users WHERE login = ?";

        try (Connection connection = connectionPool.connectToDataBase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully: " + login);
            } else {
                System.out.println("User not found: " + login);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while deleting user: " + e.getMessage());
            throw e;
        }
    }



    public void insertUser(User user) throws SQLException {
        String insertQuery = "INSERT INTO users (id, name, lastname, login, password, role_id, is_blocked) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionPool.connectToDataBase();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {

            if (!isUserPresent(user.getLogin())) {
                preparedStatement.setObject(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getLastname());
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setObject(6, user.getRole_id());
                preparedStatement.setBoolean(7, user.isBlocked());

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("User successfully inserted: " + user.getLogin());
                } else {
                    System.out.println("Failed to insert user: " + user.getLogin());
                }
            } else {
                System.out.println("User already exists.");
            }
        }
    }
}
