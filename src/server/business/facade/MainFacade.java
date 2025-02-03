package server.business.facade;

import server.business.mapper.UserMapper;
import server.business.service.UserService;
import server.data.entity.User;
import server.presentation.dto.request.CreateUserRqDto;
import server.presentation.dto.response.CreateUserRespDto;
import server.presentation.dto.response.ErrorDto;
import server.presentation.dto.response.ResponseDto;
import server.utils.exception.badrequest.UserNotFoundException;

import java.sql.SQLException;
import java.util.UUID;

public class MainFacade {

    private final UserService userService;

    private final UserMapper userMapper;

    public MainFacade() throws SQLException {
        userMapper = new UserMapper();
        userService = new UserService();
    }

    public ResponseDto<CreateUserRespDto> createUser(CreateUserRqDto createUserRqDto) throws SQLException {
        String login = createUserRqDto.login();
        if (userService.ifUserExists(login)) {
            return new ResponseDto<>(new ErrorDto("Пользователь с логином %s уже существует".formatted(login)));
        }
        User user = userMapper.toUser(createUserRqDto);
        user = userService.save(user);
        CreateUserRespDto createUserRespDto = userMapper.toCreateUserRespDto(user);
        return userMapper.toResponseDto(createUserRespDto, null);
    }

    public User findUserById(UUID id) throws SQLException {
        return userService.findUserByID(id);
    }

    public User findUserByLogin(String login) throws SQLException {
        return userService.findUserByLogin(login);
    }

    public void updateUser(String login) throws SQLException {
        userService.update(login);
    }

    public void deleteUser(String login) throws SQLException {

        if(!userService.ifUserExists(login)){
            throw new UserNotFoundException("User not found");
        }
            userService.delete(login);
    }



}
