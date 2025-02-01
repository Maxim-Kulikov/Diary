package client;

import server.data.entity.User;
import server.presentation.controller.UserController;
import server.presentation.dto.request.CreateUserRqDto;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class ClientRequest {

    UserController userController = new UserController();

    public ClientRequest() throws SQLException, IOException {
    }

    public void addUser(CreateUserRqDto createUserRqDto) throws SQLException, ConstraintViolationException, IOException {
        userController.createAccount(createUserRqDto);
    }

    public void deleteUser(String login) throws SQLException, IOException {
        userController.delete(login);
    }

    public User findUserById(UUID id) throws SQLException, IOException {
        return userController.findUserById(id);
    }
}
