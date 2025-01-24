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

    public UserRepository() throws SQLException {
    }

    @Override
    protected void finalize() throws IOException, SQLException {
    }

    public User save(User user) throws SQLException {
        getRowsInserted(user);
        return user;
    }

    public User findUserByLogin(String login) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        User user = null;

            Connection connection = connectionPool.connectToDataBase();

            String query = "SELECT * FROM users WHERE login = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);

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



    public User findUserById(UUID id) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
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
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.connectToDataBase();
        User user = findUserByLogin(login);

        String query = "SELECT EXISTS (SELECT 1 FROM users WHERE login = ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public void delete(String login) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        User user = null;

        Connection connection = connectionPool.connectToDataBase();

        String query = "DELETE FROM users WHERE login = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, login);

        int rowsInserted = preparedStatement.executeUpdate();
        System.out.println("Rows deleted: " + rowsInserted);
    }

    public List<Object> getRowsInserted(User user) throws SQLException {
        List<Object> userList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.connectToDataBase();

        String insertQuery = "INSERT INTO users (id, login, password, name, lastname, role_id, is_blocked) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

        preparedStatement.setObject(1, user.getId());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getName());
        preparedStatement.setString(5, user.getLastname());
        preparedStatement.setObject(6, user.getRole_id());
        preparedStatement.setBoolean(7, user.isBlocked());

        userList.add(user.getId());
        userList.add(user.getLogin());
        userList.add(user.getPassword());
        userList.add(user.getName());
        userList.add(user.getLastname());
        userList.add(user.getRole_id());
        userList.add(user.isBlocked());


        int rowsInserted = preparedStatement.executeUpdate();
        System.out.println("Rows inserted: " + rowsInserted);

        return userList;
    }
}
