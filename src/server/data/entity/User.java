package server.data.entity;

import server.business.enums.RoleEnum;

import java.util.Objects;
import java.util.UUID;

public class User {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", role=" + role +
                ", isBlocked=" + isBlocked +
                '}';
    }

    private UUID id;
    private String login;
    private String password;
    private String username;
    private String name;
    private String lastname;
    private RoleEnum role;
    private boolean isBlocked;

    public User() {
    }

    public User(UUID id, String login, String password, String username, String name, String lastname, RoleEnum role, boolean isBlocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return isBlocked == user.isBlocked && Objects.equals(id, user.id) && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getName(), user.getName()) && Objects.equals(getLastname(), user.getLastname()) && getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
