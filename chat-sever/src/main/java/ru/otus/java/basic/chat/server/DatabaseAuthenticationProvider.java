package ru.otus.java.basic.chat.server;

import ru.otus.java.basic.chat.Role;
import ru.otus.java.basic.chat.RoleName;
import ru.otus.java.basic.chat.User;
import ru.otus.java.basic.chat.service.DefaultRoleService;
import ru.otus.java.basic.chat.service.DefaultUserService;
import ru.otus.java.basic.chat.service.RoleService;
import ru.otus.java.basic.chat.service.UserService;

import java.sql.SQLException;

public class DatabaseAuthenticationProvider extends BaseAuthenticationProvider {
    private UserService userService;
    private RoleService roleService;

    public DatabaseAuthenticationProvider(Server server) {
        super(server);
    }

    @Override
    public void initialize() {
        try {
            this.userService = new DefaultUserService();
            this.roleService = new DefaultRoleService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean isUsernameAlreadyExists(String username) {
        return userService.isUsernameAlreadyExists(username);
    }

    @Override
    protected boolean isLoginAlreadyExists(String login) {
        return userService.isLoginAlreadyExists(login);
    }

    @Override
    protected User getUserByLoginAndPassword(String login, String password) {
        return userService.getUserByLoginAndPassword(login, password);
    }

    @Override
    protected void addUser(User user) {
        userService.addUser(user);
    }

    @Override
    protected Role getRole(RoleName roleName) {
        return roleService.getRoleByName(roleName);
    }
}
