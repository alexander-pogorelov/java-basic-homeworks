package ru.otus.java.basic.chat.service;

import ru.otus.java.basic.chat.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserByLoginAndPassword(String login, String password);
    boolean isLoginAlreadyExists(String login);
    boolean isUsernameAlreadyExists(String username);
    void addUser(User user);
}
