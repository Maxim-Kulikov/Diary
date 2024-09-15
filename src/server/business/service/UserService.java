package server.business.service;

import server.data.entity.User;
import server.data.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public Optional<User> findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
