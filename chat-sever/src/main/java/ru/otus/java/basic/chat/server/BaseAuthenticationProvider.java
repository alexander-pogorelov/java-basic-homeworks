package ru.otus.java.basic.chat.server;

import ru.otus.java.basic.chat.*;

abstract public class BaseAuthenticationProvider implements AuthenticationProvider {
    private final Server server;

    public BaseAuthenticationProvider(Server server) {
        this.server = server;
    }

    @Override
    public boolean authenticate(ClientHandler clientHandler, String login, String password) {
        User user = getUserByLoginAndPassword(login, password);
        if (user == null) {
            clientHandler.sendMessage("Некорректный логин/пароль");
            return false;
        }
        if (server.isUsernameBusy(user.getUsername())) {
            clientHandler.sendMessage("Указанная учетная запись уже используется");
            return false;
        }
        clientHandler.setUser(user);
        clientHandler.sendMessage("/authok" + user.getUsername());
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
        User user = new User(login, password, username).addRole(getRole(RoleName.USER));
        addUser(user);
        clientHandler.setUser(user);
        clientHandler.sendMessage("/regok" + username);
        return true;
    }

    abstract protected User getUserByLoginAndPassword(String login, String password);

    abstract protected boolean isUsernameAlreadyExists(String username);

    abstract protected boolean isLoginAlreadyExists(String login);

    abstract protected void addUser(User user);

    abstract protected Role getRole(RoleName roleName);
}
