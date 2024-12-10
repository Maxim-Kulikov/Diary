package server.data.repository;

import db.DatabaseDriver;
import server.data.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository {

    private final DatabaseDriver database = DatabaseDriver.INSTANCE;

    private List<User> usersRepository;

    public UserRepository() {
        this.usersRepository = new ArrayList<>();
    }

    public void transferUsersFromDatabase() throws IOException {
       usersRepository = DatabaseDriver.INSTANCE.readUserFromFile();
    }

    @Override
    protected void finalize() throws IOException {
        database.writeUsersToFile(usersRepository);
    }


    public void save(User user) throws IOException {
        usersRepository.add(user);
    }

    public Optional<User> findUserByLogin(String login) throws IOException {
        return usersRepository
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    public Optional<User> findUserById(UUID id) throws IOException {
        return usersRepository
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public void delete(UUID id) throws IOException {
        usersRepository.remove(findUserById(id));
    }
}
