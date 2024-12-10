package server.business.facade;

import server.business.mapper.UserMapper;
import server.business.service.UserService;
import server.data.entity.User;
import server.presentation.dto.request.CreateUserRqDto;
import server.presentation.dto.response.CreateUserRespDto;
import server.presentation.dto.response.ErrorDto;
import server.presentation.dto.response.ResponseDto;
import server.utils.exception.badrequest.UserNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MainFacade {

    private final UserService userService;

    private final UserMapper userMapper;

    public MainFacade() throws IOException {
        userMapper = new UserMapper();
        userService = new UserService();
    }


    public ResponseDto<CreateUserRespDto> createUser(CreateUserRqDto createUserRqDto) throws IOException {
        String login = createUserRqDto.login();
        if (userService.findUserByLogin(login).isPresent()) {
            return new ResponseDto<>(new ErrorDto("Пользователь с логином %s уже существует".formatted(login)));
        }
        User user = userMapper.toUser(createUserRqDto);
        user = userService.save(user);
        CreateUserRespDto createUserRespDto = userMapper.toCreateUserRespDto(user);
        return userMapper.toResponseDto(createUserRespDto, null);
    }

    public Optional<User> findUserById(UUID id) throws IOException {
        return userService.findUserByID(id);
    }

    public void deleteUser(UUID id) throws IOException {

        if(userService.findUserByID(id).isEmpty()){
            throw new UserNotFoundException("User not found");
        } else {
            userService.delete(id);
        }
    }


}
