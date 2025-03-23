
import client.AuthenticationService;
import client.ClientQuery;
import server.data.entity.Role;
import server.data.entity.User;
import server.utils.exception.badrequest.ConstraintViolationException;

import java.sql.SQLException;

public class Main {
    private static boolean stillRunning = true;
    public static void main(String[] args) throws SQLException, ConstraintViolationException {

        AuthenticationService authenticationService = new AuthenticationService();
        ClientQuery clientQuery = new ClientQuery();

        User user = authenticationService.login().getResult().orElse(null);

        while (stillRunning) {

            if (user.getRole_id().equals(Role.ADMIN.getUuid())) {
                clientQuery.adminMethods();
            }
            if (user.getRole_id().equals(Role.TEACHER.getUuid())) {
                clientQuery.teacherMethods();
            }
            if (user.getRole_id().equals(Role.PUPIL.getUuid())) {
                clientQuery.pupilMethods();
            }
            if (user.getRole_id() != Role.TEACHER.getUuid() && user.getRole_id() != Role.PUPIL.getUuid() && user.getRole_id() != Role.ADMIN.getUuid()) {
                stillRunning = false;
            }
        }
    }
}