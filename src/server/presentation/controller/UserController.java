package server.presentation.controller;

import server.business.facade.MainFacade;
import server.presentation.dto.request.CreateUserRqDto;
import server.presentation.dto.response.CreateUserRespDto;
import server.presentation.dto.response.ResponseDto;
import server.utils.Validator;
import server.utils.exception.badrequest.ConstraintViolationException;
import java.util.Scanner;

public class UserController {

    private final MainFacade mainFacade;
    private final Validator validator;

    public UserController() {
        validator = new Validator();
        mainFacade = new MainFacade();
    }

    public ResponseDto<CreateUserRespDto> createAccount(CreateUserRqDto createUserRqDto) throws ConstraintViolationException {

        Scanner scanner = new Scanner(System.in);

        int min = 4;
        int max = 20;
        String input = scanner.nextLine();
        String symbol = scanner.nextLine();

        if (Validator.ifLoginIsEmpty(createUserRqDto.login())) {
            return null;
        }
        if (Validator.ifInputIsSmallerThanMin(createUserRqDto.login().length(), min) || Validator.ifInputIsSmallerThanMin(createUserRqDto.password().length(), min)){
            return null;
        }
        if (Validator.ifInputIsGreaterThanMax(createUserRqDto.login().length(), max) || Validator.ifInputIsGreaterThanMax(createUserRqDto.password().length(), max)){
            return null;
        }
        if (Validator.IsContainingElement(min, max, input, symbol )) {
            return null;
        }


        return mainFacade.createUser(createUserRqDto);
    }
}

