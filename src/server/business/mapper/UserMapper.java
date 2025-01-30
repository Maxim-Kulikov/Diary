package server.business.mapper;

import server.data.entity.User;
import server.presentation.dto.request.CreateUserRqDto;
import server.presentation.dto.response.CreateUserRespDto;
import server.presentation.dto.response.ErrorDto;
import server.presentation.dto.response.ResponseDto;

public class UserMapper {

    public <T> ResponseDto<T> toResponseDto(T result, ErrorDto errorDto) {
        if (result == null) {
            return new ResponseDto<>(errorDto);
        }
        return new ResponseDto<>(result, errorDto);
    }

    public CreateUserRespDto toCreateUserRespDto(User user) {
        return new CreateUserRespDto(user.getId(), user.getLogin(), user.getName(), user.getLastname(), user.getRole_id(), user.isBlocked());
    }

    public User toUser(CreateUserRqDto createUserRqDto) {
        User user = new User();
        user.setLogin(createUserRqDto.login());
        user.setPassword(createUserRqDto.password());
        user.setName(createUserRqDto.name());
        user.setLastname(createUserRqDto.lastname());
        user.setId(createUserRqDto.role());
        user.setRole_id(createUserRqDto.role());
        return user;
    }

}
