import db.DatabaseDriver;

import server.business.enums.RoleEnum;
import server.business.facade.MainFacade;
import server.data.entity.User;
import server.data.repository.UserRepository;
import server.presentation.controller.UserController;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws ConstraintViolationException, IOException {

        MainFacade facade = new MainFacade();
        UserController userController = new UserController();
        UserRepository userRepository = new UserRepository();

        DatabaseDriver.INSTANCE.writeUsersToFile(List.of(new User(UUID.randomUUID(), "nigger", "123", "jkaka", "Kirill","Savenko", RoleEnum.ADMIN, true)));
        //System.out.println(DatabaseDriver.INSTANCE.readUserFromFile());

        System.out.println(userRepository.findUserByLogin("nigger"));

    }
}