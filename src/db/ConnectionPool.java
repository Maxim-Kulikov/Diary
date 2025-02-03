package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final List<Connection> availableConnections = new ArrayList<>();
    private final List<Connection> usedConnections = new ArrayList<>();
    private final int MAX_POOL_SIZE;
    private static ConnectionPool instance;

    public static ConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionPool("jdbc:postgresql://localhost:5432/Diary", "postgres", "postgres", 10);
        }
        return instance;
    }

    private ConnectionPool(String jdbcUrl, String username, String password, int MAX_POOL_SIZE) throws SQLException {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.MAX_POOL_SIZE = MAX_POOL_SIZE;
        for (int i = 0; i <= MAX_POOL_SIZE; i++) {
            availableConnections.add(DriverManager.getConnection(jdbcUrl, username, password));
        }
    }

    public synchronized Connection connectToDataBase() throws SQLException {
        if (availableConnections.isEmpty()) {
            System.out.println("No available connections in the pool.");
        }
        Connection connection = availableConnections.remove(availableConnections.size() - 1);
        usedConnections.add(connection);
        System.out.println("Got connection: " + connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            usedConnections.remove(connection);
            availableConnections.add(connection);
        }
    }

    public synchronized void closeAllConnections() throws SQLException {
        for (Connection connection : availableConnections) {
            connection.close();
        }
        for (Connection connection : usedConnections) {
            connection.close();
        }
        availableConnections.clear();
        usedConnections.clear();
    }
}