package server.business.service;

import server.data.entity.User;
import server.data.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;

    public UserService() throws IOException {
        this.userRepository = new UserRepository();
    }

    public Optional<User> findUserByLogin(String login) throws IOException {
        return userRepository.findUserByLogin(login);
    }

    public User save(User user) throws IOException {
        user.setId(UUID.randomUUID());
        userRepository.save(user);
        return user;
    }

    public Optional<User> findUserByID(UUID id) throws IOException {
        return userRepository.findUserById(id);
    }

    public void delete(UUID id) throws IOException {
        userRepository.delete(id);
    }



}
