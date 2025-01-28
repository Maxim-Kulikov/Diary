package server.business.service;

import server.data.entity.User;
import server.data.repository.UserRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;

    public UserService() throws SQLException {
        this.userRepository = new UserRepository();
    }

    public User findUserByLogin(String login) throws SQLException {
        return userRepository.findUserByLogin(login);
    }

    public User save(User user) throws SQLException {
        user.setId(UUID.randomUUID());
        userRepository.save(user);
        return user;
    }

    public User findUserByID(UUID id) throws SQLException {
        return userRepository.findUserById(id);
    }

    public boolean ifUserExists(String login) throws SQLException {
        return userRepository.isUserPresent(login);
    }

    public void delete(String login) throws SQLException {
        userRepository.delete(login);
    }



}
