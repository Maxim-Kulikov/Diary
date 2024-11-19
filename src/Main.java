import server.presentation.controller.UserController;
import server.presentation.dto.request.CreateUserRqDto;
import server.utils.exception.badrequest.ConstraintViolationException;

public class Main {
    public static void main(String[] args) throws ConstraintViolationException {
        UserController userController = new UserController();
        System.out.println(userController.createAccount(new CreateUserRqDto(null, "password", null, null, null)));
    }
}