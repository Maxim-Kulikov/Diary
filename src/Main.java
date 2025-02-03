import client.ClientQuery;

import server.utils.exception.badrequest.ConstraintViolationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final boolean stillRunning = true;
    public static void main(String[] args) throws SQLException, IOException, ConstraintViolationException {
        ClientQuery request = new ClientQuery();
        if (request.checkUserRole().equals("admin")){
            while (stillRunning) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Type 1 to add user");
                System.out.println("Type 2 to delete user");
                System.out.println("Type 3 to edit user");
                System.out.println("Type 4 to exit");

                int numberProcess = scanner.nextInt();

                if (numberProcess == 1) {
                    request.addUser();
                } else if (numberProcess == 2) {
                    request.deleteUser();
                } else if (numberProcess == 3) {
                    request.updateUser(request.userDetails().getLogin());
                } else if (numberProcess == 4) {
                    break;
                }
            }
        } else {
            System.out.println("Wrong username or password");
        }
    }
}