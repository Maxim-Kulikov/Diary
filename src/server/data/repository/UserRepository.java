package server.data.repository;

import db.Database;
import server.data.entity.User;

import java.util.Optional;

public class UserRepository {

    private final Database database = Database.INSTANCE;

    public User save(User user) {
        return database.save(user);
    }

    public Optional<User> findUserByLogin(String login) {
        return database.findAllUsers()
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }
}
