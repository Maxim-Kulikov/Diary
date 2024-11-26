package db;

import server.data.entity.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public enum DatabaseDriver {
    INSTANCE;

    public void writeUsersToFile(List<User> userList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/db/files/users.txt"))) {
            for (User user : userList) {
                writer.write(user.toString() + "\n");
            }
        }
    }

    public List<User> readUsersFromFile() {
        return null;
    }
}
