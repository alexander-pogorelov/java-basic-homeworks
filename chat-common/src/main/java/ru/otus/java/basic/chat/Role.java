package ru.otus.java.basic.chat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Role {
    private int id;
    private final RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }

    public Role(int id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public RoleName getName() {
        return name;
    }

    public static Role createFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        RoleName roleName = RoleName.fromString(rs.getString("name"));

        return new Role(id, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    public static ArrayList<Role> createFromResulData(String roleIds, String roleNames) {
        ArrayList<Role> roles = new ArrayList<>();
        String[] ids = roleIds.trim().split(",");
        String[] names = roleNames.trim().split(",");
        for (int i = 0; i < ids.length; i++) {
            roles.add(new Role(Integer.parseInt(ids[i]), RoleName.fromString(names[i])));
        }
        return roles;
    }
}
