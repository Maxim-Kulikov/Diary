package db;

import server.business.enums.RoleEnum;
import server.data.entity.User;
import server.utils.exception.badrequest.UserAlreadyExistsException;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public enum DatabaseDriver {
    INSTANCE;

    public void writeUsersToFile(List<User> userList) throws IOException {
        File database = new File("database.txt");
        database.createNewFile();

            FileWriter fileWriter = new FileWriter(database, true);

                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(userList.toString() + "\n");
                bufferedWriter.close();
    }

    public List<User> readUserFromFile() throws IOException {

        List<User> userList = new ArrayList<>();
        File myObj = new File("database.txt");
        Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();

            String finalLine = data.substring(6, data.length() - 2);
            finalLine = finalLine.replace(" ", "");
            finalLine = finalLine.replace("'", "");
            String[] keyValuePairs = finalLine.split(",");
            User user = new User();
            for (int i = 0; i < keyValuePairs.length; i++) {
                String value = keyValuePairs[i].split("=")[1];

                switch (i) {
                    case 0: user.setId(UUID.fromString(value));
                    break;
                    case 1: user.setLogin(value);
                    break;
                    case 2: user.setPassword(value);
                    break;
                    case 3: user.setName(value);
                    break;
                    case 4: user.setLastname(value);
                    break;
                    case 5: user.setRole(UUID.fromString(value));
                    break;
                    case 6: user.setBlocked(Boolean.getBoolean(value));
                }
            }
            userList.add(user);
        return userList;
    }

    public int getRowsInserted(User user) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.connectToDataBase();

        String insertQuery = "INSERT INTO users (id, login, password, name, lastname, role_id, is_blocked) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

        preparedStatement.setObject(1, user.getId());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getName());
        preparedStatement.setString(5, user.getLastname());
        preparedStatement.setObject(6, user.getRole());
        preparedStatement.setBoolean(7, user.isBlocked());

        int rowsInserted = preparedStatement.executeUpdate();
        System.out.println("Rows inserted: " + rowsInserted);
        return rowsInserted;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}





