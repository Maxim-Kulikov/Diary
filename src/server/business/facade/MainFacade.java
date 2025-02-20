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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MainFacade {

    private final UserService userService;

    private final UserMapper userMapper;

    public MainFacade() throws IOException, SQLException {
        userMapper = new UserMapper();
        userService = new UserService();
    }

    public ResponseDto<CreateUserRespDto> createUser(CreateUserRqDto createUserRqDto) throws IOException, SQLException {
        String login = createUserRqDto.login();
        if (userService.ifUserExists(login)) {
            return new ResponseDto<>(new ErrorDto("Пользователь с логином %s уже существует".formatted(login)));
        }
        User user = userMapper.toUser(createUserRqDto);
        user = userService.save(user);
        CreateUserRespDto createUserRespDto = userMapper.toCreateUserRespDto(user);
        return userMapper.toResponseDto(createUserRespDto, null);
    }

    public User findUserById(UUID id) throws IOException, SQLException {
        return userService.findUserByID(id);
    }

    public void deleteUser(String login) throws IOException, SQLException {

        if(userService.ifUserExists(login) == true){
            throw new UserNotFoundException("User not found");
        }
            userService.delete(login);
    }


}
