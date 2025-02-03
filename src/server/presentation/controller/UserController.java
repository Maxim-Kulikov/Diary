package server.presentation.controller;

import server.business.facade.MainFacade;
import server.data.entity.User;
import server.presentation.dto.request.CreateUserRqDto;
import server.presentation.dto.response.CreateUserRespDto;
import server.presentation.dto.response.ResponseDto;
import server.utils.Validator;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.sql.SQLException;
import java.util.UUID;

public class UserController {

    private final MainFacade mainFacade;

    public UserController() throws SQLException {
        mainFacade = new MainFacade();
    }

    public ResponseDto<CreateUserRespDto> createAccount(CreateUserRqDto createUserRqDto) throws ConstraintViolationException, SQLException {
        Validator.notNull(createUserRqDto.login());
        Validator.length(createUserRqDto.login(), 0, 20);

        return mainFacade.createUser(createUserRqDto);
    }

    public void delete(String login) throws SQLException {
        mainFacade.deleteUser(login);
    }

    public User findUserById(UUID id) throws SQLException {
         return mainFacade.findUserById(id);
    }

    public User findUserByLogin(String login) throws SQLException {
        return mainFacade.findUserByLogin(login);
    }

    public void updateUser(String login) throws SQLException {
        mainFacade.updateUser(login);
    }
}

