package client;

import server.data.entity.User;
import server.presentation.controller.UserController;
import server.presentation.dto.request.CreateUserRqDto;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class ClientQuery {

    UserController userController = new UserController();

    public ClientQuery() throws SQLException {
    }

    public void addUser() throws SQLException, ConstraintViolationException {

        User user = userDetails();
        CreateUserRqDto createUser1 = new CreateUserRqDto(user.getLogin(), user.getPassword(), user.getName(), user.getLastname(), user.getRole_id());
        userController.createAccount(createUser1);
    }

    public void deleteUser() throws SQLException {
        String login = userDetails().getLogin();
        userController.delete(login);
    }

    public User findUserById(UUID id) throws SQLException {
        return userController.findUserById(id);
    }

    public User findUserByLogin(String login) throws SQLException {
        return userController.findUserByLogin(login);
    }

    public User userDetails(){
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
        return new User(UUID.randomUUID(), name, lastname, login, password, role, true);
    }

    public String checkUserRole() throws SQLException {

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter login: ");
        String login = scanner1.nextLine();
        System.out.println("Enter password: ");
        String password = scanner1.nextLine();
        if (login.equals(findUserById(UUID.fromString("df916329-02dc-4260-a98a-c6be8451d393")).getLogin()) && password.equals(findUserById(UUID.fromString("df916329-02dc-4260-a98a-c6be8451d393")).getPassword())){
            return "admin";
        }
        if (findUserById(findUserByLogin(login).getId()).getRole_id() == UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0183")){
            return "pupil";
        }
        if (findUserById(findUserByLogin(login).getId()).getRole_id() == UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0185")){
            return "teacher";
        }
        if (password.isEmpty() || login.isEmpty()){
            System.out.println("Invalid login or password");
        }
        return null;
    }

    public void updateUser(String login) throws SQLException {
        userController.updateUser(login);
    }

}
