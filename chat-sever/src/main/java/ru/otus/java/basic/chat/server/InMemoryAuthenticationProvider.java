package ru.otus.java.basic.chat.server;

import ru.otus.java.basic.chat.User;
import ru.otus.java.basic.chat.ValidationException;
import ru.otus.java.basic.chat.Validator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticationProvider implements AuthenticationProvider {
    private final Server server;
    private final List<User> users;

    public InMemoryAuthenticationProvider(Server server) {
        this.server = server;
        this.users = new CopyOnWriteArrayList<>();
    }

    @Override
    public void initialize() {
        addDefaultUses();
    }

    @Override
    public boolean authenticate(ClientHandler clientHandler, String login, String password) {
        String authUsername = getUsernameByLoginAndPassword(login, password);
        if (authUsername == null) {
            clientHandler.sendMessage("Некорректный логин/пароль");
            return false;
        }
        if (server.isUsernameBusy(authUsername)) {
            clientHandler.sendMessage("Указанная учетная запись уже используется");
            return false;
        }
        clientHandler.setUsername(authUsername);
        clientHandler.sendMessage("/authok" + authUsername);
        return true;
    }

    @Override
    public boolean register(ClientHandler clientHandler, String login, String password, String username) {
        try {
            Validator.validateLogin(login);
            Validator.validatePassword(password);
        } catch (ValidationException e) {
            clientHandler.sendMessage(e.getMessage());
            return false;
        }
        if (isLoginAlreadyExists(login)) {
            clientHandler.sendMessage("Такой логин уже занят");
            return false;
        }
        if (isUsernameAlreadyExists(username)) {
            clientHandler.sendMessage("Такое имя пользователя уже занято");
            return false;
        }
        users.add(new User(login, password, username));
        clientHandler.setUsername(username);
        clientHandler.sendMessage("/regok" + username);
        return true;
    }

    private void addDefaultUses() {
        users.add(new User("qwe", "qwe", "qwe1"));
        users.add(new User("asd", "asd", "asd1"));
        users.add(new User("zxc", "zxc", "zxc1"));
    }

    private String getUsernameByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return null;
    }

    private boolean isLoginAlreadyExists(String login) {
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameAlreadyExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
}
