package server.presentation.dto.response;

import java.util.UUID;

public record CreateUserRespDto(UUID id, String login, String name, String lastname, boolean isBlocked) {
}
