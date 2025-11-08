package ru.otus.java.basic.chat.server;

import ru.otus.java.basic.chat.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticationProvider extends BaseAuthenticationProvider {
    private final List<User> users;

    public InMemoryAuthenticationProvider(Server server) {
        super(server);
        this.users = new CopyOnWriteArrayList<>();
    }

    @Override
    public void initialize() {
        addDefaultUses();
    }

    @Override
    protected User getUserByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    protected boolean isLoginAlreadyExists(String login) {
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean isUsernameAlreadyExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void addUser(User user) {
        users.add(user);
    }

    @Override
    protected Role getRole(RoleName roleName) {
        return new  Role(roleName);
    }

    private void addDefaultUses() {
        users.add(new User("qwe", "qwe", "qwe1").addRole(RoleName.USER));
        users.add(new User("asd", "asd", "asd1").addRole(RoleName.USER));
        users.add(new User("zxc", "zxc", "zxc1").addRole(RoleName.USER));
        users.add(new User("adm", "adm", "admin").addRole(RoleName.ADMIN));
    }
}
