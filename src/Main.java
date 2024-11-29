import db.DatabaseDriver;
import server.data.entity.User;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws ConstraintViolationException, IOException {
        DatabaseDriver.INSTANCE.writeUsersToFile(List.of(new User(UUID.randomUUID(), "Zlll", "", "", "","")));
        DatabaseDriver.INSTANCE.readUserFromFile();
    }
}