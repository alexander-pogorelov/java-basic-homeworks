package ru.otus.java.basic.chat.service;

import ru.otus.java.basic.chat.Role;
import ru.otus.java.basic.chat.RoleName;

import java.sql.*;

public class DefaultRoleService implements RoleService {
    private final Connection connection;

    public DefaultRoleService() throws SQLException {
        String dbUrl = System.getenv("POSTGRES_DB_URL");
        String dbUser = System.getenv("POSTGRES_USER");
        String dbPassword = System.getenv("POSTGRES_PASSWORD");
        this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public Role getRoleByName(RoleName roleName) {
        try (PreparedStatement ps = connection.prepareStatement(getRoleByNameSql())) {
            ps.setString(1, roleName.toSqlName());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Role.createFromResultSet(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRoleByNameSql() {
        return """
                select r.id, r.name from public.role r where name = ?;
                """;
    }
}
