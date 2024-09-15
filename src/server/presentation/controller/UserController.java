package server.presentation.controller;

import server.business.facade.MainFacade;
import server.presentation.dto.request.CreateUserRqDto;
import server.presentation.dto.response.CreateUserRespDto;
import server.presentation.dto.response.ResponseDto;

public class UserController {

    private final MainFacade mainFacade;

    public UserController() {
        mainFacade = new MainFacade();
    }

    public ResponseDto<CreateUserRespDto> createAccount(CreateUserRqDto createUserRqDto) {
        if (createUserRqDto.login() == null || createUserRqDto.password() == null) {
            return null;
        }
        return mainFacade.createUser(createUserRqDto);
    }
}
