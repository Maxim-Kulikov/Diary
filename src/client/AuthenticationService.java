package client;

import server.data.entity.User;
import server.presentation.controller.UserController;
import server.presentation.dto.response.ErrorDto;
import server.presentation.dto.response.ResponseDto;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class AuthenticationService {

    private final UserController userController;
    private final Scanner scanner;

    public AuthenticationService() throws SQLException {
        userController = new UserController();
        scanner = new Scanner(System.in);
    }

    public ResponseDto<User> login() throws SQLException {
        System.out.println("Please enter your login: ");
        String userLoginStr = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String userPasswordStr = scanner.nextLine();

        if (validateLogin(userLoginStr) && validatePassword(userPasswordStr)) {
            ResponseDto<User> userResponse = userController.findUserByLogin(userLoginStr);

            if (userResponse.getResult().isPresent() &&
                    userResponse.getResult().get().getPassword().equals(userPasswordStr)) {
                return userResponse;
            }
        }

        return new ResponseDto<>(Optional.empty(), new ErrorDto("Invalid login or password"));
    }

    private boolean validateLogin(String login) throws SQLException {
        if (login == null) {
            System.out.println("Enter valid login");
            return false;
        }

        if (checkForSpecialCharacters(login)) {
            System.out.println("Login contains special characters");
            return false;
        }

        ResponseDto<User> userResponse = userController.findUserByLogin(login);

        if (userResponse.getResult().isEmpty()) {
            System.out.println("User not found");
            return false;
        }

        return true;
    }

    private boolean validatePassword(String password) {

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty");
            return false;
        }
        if (checkForSpecialCharacters(password)) {
            System.out.println("Password contains special characters");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkForSpecialCharacters(String input) {
        char[] array = input.toCharArray();
        for (char c : array) {
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }
}