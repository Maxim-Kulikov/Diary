import db.ConnectionPool;
import db.DatabaseDriver;
import server.business.enums.RoleEnum;
import server.data.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        DatabaseDriver.INSTANCE.writeUsersToFile(List.of(new User(UUID.randomUUID(), "bith", "3432", "NIgga","MAddakena", UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0183"), true)));
        DatabaseDriver.INSTANCE.getRowsInserted(new User(UUID.randomUUID(), "bith", "3432", "NIgga","MAddakena", UUID.fromString("d65e91f2-68bd-4578-93cf-e0bc3ddd0183"), true));

    }
}