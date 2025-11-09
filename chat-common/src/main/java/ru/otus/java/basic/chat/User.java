package ru.otus.java.basic.chat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private final String login;
    private final String password;
    private final String username;
    private List<Role> roles;

    public User(String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;
        this.roles = new ArrayList<>();
    }

    public User(int id, String login, String password, String username) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.username = username;
        this.roles = new ArrayList<>();
    }

    public User addRole(Role role) {
        roles.add(role);
        return this;
    }

    public User addRole(RoleName roleName) {
        roles.add(new Role(roleName));
        return this;
    }

    public User setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public int getId() {
        return id;
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

    public List<Role> getRoles() {
        return roles;
    }

    public boolean isAdmin() {
        for (Role role : roles) {
            if (RoleName.ADMIN.equals(role.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }

    public static User createFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String login = rs.getString("login");
        String password = rs.getString("password");
        String username = rs.getString("username");
        String roleIds = rs.getString("role_ids");
        String roleNames = rs.getString("role_names");

        List<Role> roles = Role.createFromResulData(roleIds, roleNames);

        return new User(id, login, password, username).setRoles(roles);
    }
}
