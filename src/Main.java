import server.presentation.controller.UserController;
import server.presentation.dto.request.CreateUserRqDto;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        System.out.println(userController.createAccount(new CreateUserRqDto(null, "password", null, null, null)));
    }
}