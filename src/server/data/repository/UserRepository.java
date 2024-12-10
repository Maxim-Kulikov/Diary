package server.data.repository;

import db.DatabaseDriver;
import server.data.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository {

    private final DatabaseDriver database = DatabaseDriver.INSTANCE;

    private final List<User> userList;

    public UserRepository() throws IOException {
        this.userList = DatabaseDriver.INSTANCE.readUserFromFile();
    }


    @Override
    protected void finalize() throws IOException {
        database.writeUsersToFile(userList);
    }


    public User save(User user) {
        userList.add(user);
        return user;
    }

    public Optional<User> findUserByLogin(String login) {
        return userList
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    public Optional<User> findUserById(UUID id) {
        return userList
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public void delete(UUID id) {
        userList.remove(findUserById(id));
    }
}
