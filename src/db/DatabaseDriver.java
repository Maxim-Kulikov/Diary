package db;

import server.data.entity.User;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public enum DatabaseDriver {
    INSTANCE;

    public void writeUsersToFile(List<User> UserList) {
        File database = new File("database.txt");
        try{
            if(!database.exists()){
                database.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(database, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(UserList.toString() + "\n");
            bufferedWriter.close();

        } catch(IOException e) {
            System.out.println("Error writing to file");
        }
    }

    public void readUserFromFile() {
            try {
                File myObj = new File("database.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        }
    }


