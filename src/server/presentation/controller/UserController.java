package server.presentation.controller;

import server.business.facade.MainFacade;
import server.data.repository.UserRepository;
import server.presentation.dto.request.CreateUserRqDto;
import server.presentation.dto.response.CreateUserRespDto;
import server.presentation.dto.response.ResponseDto;
import server.utils.Validator;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class UserController {

    private final MainFacade mainFacade;
    private UserRepository userRepository;

    public UserController() throws IOException, SQLException {
        mainFacade = new MainFacade();
    }

    public ResponseDto<CreateUserRespDto> createAccount(CreateUserRqDto createUserRqDto) throws ConstraintViolationException, IOException, SQLException {
        Validator.notNull(createUserRqDto.login());
        Validator.length(createUserRqDto.login(), 0, 20);

        return mainFacade.createUser(createUserRqDto);
    }

    public void delete(String login) throws IOException, SQLException {
        mainFacade.deleteUser(login);
    }
}

