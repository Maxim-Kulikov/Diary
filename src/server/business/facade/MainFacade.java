package server.business.facade;

import server.business.mapper.UserMapper;
import server.business.service.UserService;
import server.data.entity.User;
import server.presentation.dto.request.CreateUserRqDto;
import server.presentation.dto.response.CreateUserRespDto;
import server.presentation.dto.response.ErrorDto;
import server.presentation.dto.response.ResponseDto;

public class MainFacade {

    private final UserService userService;

    private final UserMapper userMapper;

    public MainFacade() {
        userMapper = new UserMapper();
        userService = new UserService();
    }

    public ResponseDto<CreateUserRespDto> createUser(CreateUserRqDto createUserRqDto) {
        String login = createUserRqDto.login();
        if (userService.findUserByLogin(login).isPresent()) {
            return new ResponseDto<>(new ErrorDto("Пользователь с логином %s уже существует".formatted(login)));
        }
        User user = userMapper.toUser(createUserRqDto);
        user = userService.save(user);
        CreateUserRespDto createUserRespDto = userMapper.toCreateUserRespDto(user);
        return userMapper.toResponseDto(createUserRespDto, null);
    }

}
