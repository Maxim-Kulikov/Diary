package db;

import server.business.enums.RoleEnum;
import server.data.entity.User;

import java.io.*;
import java.util.*;

public enum DatabaseDriver {
    INSTANCE;

    public void writeUsersToFile(List<User> userList) throws IOException {
        File database = new File("database.txt");
            if (!database.exists()) {
                database.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(database, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userList.toString() + "\n");
            bufferedWriter.close();
    }

    public List<User> readUserFromFile() throws IOException {

        List<User> userList = new ArrayList<>();
        File myObj = new File("database.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
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
                    case 3: user.setUsername(value);
                    break;
                    case 4: user.setName(value);
                    break;
                    case 5: user.setLastname(value);
                    break;
                    case 6: user.setRole(RoleEnum.valueOf(value));
                    break;
                    case 7: user.setBlocked(Boolean.getBoolean(value));
                }
            }
            userList.add(user);
        }
        return userList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}