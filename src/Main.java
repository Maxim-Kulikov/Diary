import client.ClientRequest;
import server.data.entity.User;
import server.presentation.dto.request.CreateUserRqDto;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final boolean stillRunning = true;
    public static void main(String[] args) throws SQLException, IOException, ConstraintViolationException {
        ClientRequest request = new ClientRequest();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter admin login: ");
        String adminLogin = scanner1.nextLine();
        System.out.println("Enter admin password: ");
        String adminPassword = scanner1.nextLine();

        if (adminLogin.equals(request.findUserById(UUID.fromString("df916329-02dc-4260-a98a-c6be8451d393")).getLogin()) && adminPassword.equals(request.findUserById(UUID.fromString("df916329-02dc-4260-a98a-c6be8451d393")).getPassword())) {

            while (stillRunning) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Type 1 to add user");
                System.out.println("Type 2 to delete user");

                int numberProcess = scanner.nextInt();

                if (numberProcess == 1) {
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Type name: ");
                    String name = sc1.nextLine();
                    System.out.println("Type lastname: ");
                    String lastname = sc1.nextLine();
                    System.out.println("Type login: ");
                    String login = sc1.nextLine();
                    System.out.println("Type password: ");
                    String password = sc1.nextLine();
                    System.out.println("Type role: ");
                    String role_id = sc1.nextLine();
                    UUID role = null;
                    if (role_id.equals("pupil")) {
                        role = UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0183");
                    } else if (role_id.equals("teacher")) {
                        role = UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0185");
                    } else if (role_id.equals("admin")) {
                        role = UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0187");
                    }
                    User user = new User(UUID.randomUUID(), name, lastname, login, password, role, true);
                    CreateUserRqDto createUser1 = new CreateUserRqDto(user.getLogin(), user.getPassword(), user.getName(), user.getLastname(), user.getRole_id());
                    ClientRequest clientRequest = new ClientRequest();
                    clientRequest.addUser(createUser1);
                } else if (numberProcess == 2) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Type name: ");
                    String name = sc.nextLine();
                    System.out.println("Type lastname: ");
                    String lastname = sc.nextLine();
                    System.out.println("Type login: ");
                    String login = sc.nextLine();
                    System.out.println("Type password: ");
                    String password = sc.nextLine();
                    System.out.println("Type role: ");
                    String role_id = sc.nextLine();
                    UUID role = null;
                    if (role_id.equals("pupil")) {
                        role = UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0183");
                    } else if (role_id.equals("teacher")) {
                        role = UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0185");
                    }
                    User user = new User(UUID.randomUUID(), name, lastname, login, password, role, true);
                    CreateUserRqDto createUser2 = new CreateUserRqDto(user.getLogin(), user.getPassword(), user.getName(), user.getLastname(), user.getRole_id());
                    ClientRequest clientRequest = new ClientRequest();
                    clientRequest.deleteUser(user.getLogin());
                }
            }
        } else {
            System.out.println("Wrong username or password");
        }
    }
}