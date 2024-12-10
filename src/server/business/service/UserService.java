package server.business.service;

import server.data.entity.User;
import server.data.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public Optional<User> findUserByLogin(String login) throws IOException {
        return userRepository.findUserByLogin(login);
    }

    public void save(User user) throws IOException {
        userRepository.save(user);
    }

    public Optional<User> findUserByID(UUID id) throws IOException {
        return userRepository.findUserById(id);
    }

    public void delete(UUID id) throws IOException {
        userRepository.delete(id);
    }



}
