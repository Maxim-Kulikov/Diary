package server.presentation.dto.request;

public record CreateUserRqDto(String login, String password, String username, String name, String lastname) {
}
