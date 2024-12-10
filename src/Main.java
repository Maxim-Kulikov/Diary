import db.DatabaseDriver;

import server.business.enums.RoleEnum;
import server.data.entity.User;
import server.data.repository.UserRepository;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws ConstraintViolationException, IOException {


        UserRepository userRepository = new UserRepository();

        DatabaseDriver.INSTANCE.writeUsersToFile(List.of(new User(UUID.randomUUID(), "nigger", "123", "kaimentalityy", "Kirill","Savenko", RoleEnum.ADMIN, true)));


    }
}