package ru.otus.java.basic.chat;

public class User {
    private final String login;
    private final String password;
    private final String username;
    private final Role role;

    public User(String login, String password, String username, Role role) {
        this.login = login;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }
}
