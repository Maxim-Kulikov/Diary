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

        while (stillRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type 1 to add user");
            System.out.println("Type 2 to delete user");

            int numberProcess = scanner.nextInt();

            if(numberProcess == 1){
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
                }
                else if (role_id.equals("teacher")) {
                    role = UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0185");
                }
                User user = new User(UUID.randomUUID(), name, lastname,login, password, role, true);
                CreateUserRqDto createUserRqDto = new CreateUserRqDto(user.getLogin(), user.getPassword(), user.getName(), user.getLastname(), user.getRole_id());
                ClientRequest clientRequest = new ClientRequest();
                clientRequest.addUser(createUserRqDto);
            }
            else if(numberProcess == 2){
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
                }
                else if (role_id.equals("teacher")) {
                    role = UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0185");
                }
                User user = new User(UUID.randomUUID(), name, lastname,login, password, role, true);
                CreateUserRqDto createUserRqDto = new CreateUserRqDto(user.getLogin(), user.getPassword(), user.getName(), user.getLastname(), user.getRole_id());
                ClientRequest clientRequest = new ClientRequest();
                clientRequest.deleteUser(user);
            }
        }
    }
}