package db;

import server.data.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum Database {

    INSTANCE;

    private final List<User> userList = new ArrayList<>();

    public List<User> findAllUsers() {
        return userList;
    }

    public User save(User user) {
        user.setId(UUID.randomUUID());
        userList.add(user);
        return user;
    }

}
