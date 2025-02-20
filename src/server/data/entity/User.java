package server.data.entity;

import java.util.Objects;
import java.util.UUID;

public class User {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", role_id=" + role_id +
                ", isBlocked=" + isBlocked +
                '}';
    }

    private UUID id;
    private String login;
    private String password;
    private String name;
    private String lastname;
    private UUID role_id;
    private boolean isBlocked;

    public User() {
    }

    public User(UUID id, String login, String password, String name, String lastname, UUID role, boolean isBlocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.role_id = role;
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

    public UUID getRole_id() {
        return role_id;
    }

    public void setRole_id(UUID role_id) {
        this.role_id = role_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return isBlocked == user.isBlocked && Objects.equals(id, user.id) && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getName(), user.getName()) && Objects.equals(getLastname(), user.getLastname()) && getRole_id() == user.getRole_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
