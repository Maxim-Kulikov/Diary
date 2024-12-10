package server.business.facade;

import server.business.mapper.UserMapper;
import server.business.service.UserService;
import server.data.entity.User;
import server.presentation.dto.request.CreateUserRqDto;
import server.presentation.dto.response.CreateUserRespDto;
import server.presentation.dto.response.ErrorDto;
import server.presentation.dto.response.ResponseDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MainFacade {

    private final UserService userService;

    private final UserMapper userMapper;

    private User user;

    public MainFacade() {
        userMapper = new UserMapper();
        userService = new UserService();
        user = new User();
    }

    List<User> users = new ArrayList<>();

    public ResponseDto<CreateUserRespDto> createUser(CreateUserRqDto createUserRqDto) throws IOException {
        String login = createUserRqDto.login();
        if (userService.findUserByLogin(login).isPresent()) {
            return new ResponseDto<>(new ErrorDto("Пользователь с логином %s уже существует".formatted(login)));
        }
        User user = userMapper.toUser(createUserRqDto);
        userService.save(user);
        CreateUserRespDto createUserRespDto = userMapper.toCreateUserRespDto(user);
        return userMapper.toResponseDto(createUserRespDto, null);
    }

    public Optional<User> findUserById(UUID id) throws IOException {
        return userService.findUserByID(id);
    }

    public void deleteUser(UUID id) throws IOException {
        userService.delete(id);
    }


}
