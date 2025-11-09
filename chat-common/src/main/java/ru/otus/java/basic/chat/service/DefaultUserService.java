package ru.otus.java.basic.chat.service;

import ru.otus.java.basic.chat.Role;
import ru.otus.java.basic.chat.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserService implements UserService {
    private final Connection connection;

    public DefaultUserService() throws SQLException {
        String dbUrl = System.getenv("POSTGRES_DB_URL");
        String dbUser = System.getenv("POSTGRES_USER");
        String dbPassword = System.getenv("POSTGRES_PASSWORD");
        this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(getAllSql())) {
                while (rs.next()) {
                    User user = User.createFromResultSet(rs);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        try (PreparedStatement ps = connection.prepareStatement(getUserByLoginAndPasswordSql())) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return User.createFromResultSet(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isLoginAlreadyExists(String login) {
        boolean exists = false;
        try (PreparedStatement ps = connection.prepareStatement(isLoginAlreadyExistsSql())) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt(1) == 1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

    @Override
    public boolean isUsernameAlreadyExists(String username) {
        boolean exists = false;
        try (PreparedStatement ps = connection.prepareStatement(isUsernameAlreadyExistsSql())) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt(1) == 1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

    @Override
    public void addUser(User user) {
        try {
            connection.setAutoCommit(false);
            int userId;
            try (PreparedStatement ps = connection.prepareStatement(addUserSql(), Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getUsername());
                ps.executeUpdate();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Не удалось получить ID пользователя после вставки.");
                    }
                }
            }
            try (PreparedStatement pstmt = connection.prepareStatement(addUserRoleSql())) {
                for (Role role : user.getRoles()) {
                    pstmt.setInt(1, userId);
                    pstmt.setInt(2, role.getId());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                e.addSuppressed(rollbackException);
            }
            throw new RuntimeException("Ошибка при добавлении пользователя", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Ошибка при восстановлении auto-commit: " + e.getMessage());
                }
            }
        }
    }

    private String getAllSql() {
        return """
                select u.id, u.login, u.password, u.username, string_agg(r.id::TEXT, ',') as role_ids, string_agg(r.name, ',') as role_names from public."user" u
                inner join public.user_role ur on u.id = ur.user_id
                inner join public.role r on r.id = ur.role_id
                group by u.id;
                """;
    }

    private String getUserByLoginAndPasswordSql() {
        return """
                select u.id, u.login, u.password, u.username, string_agg(r.id::TEXT, ',') as role_ids, string_agg(r.name, ',') as role_names from public."user" u
                inner join public.user_role ur on u.id = ur.user_id
                inner join public.role r on r.id = ur.role_id
                where login = ? and password = ?
                group by u.id;
                """;
    }

    private String isLoginAlreadyExistsSql() {
        return """
                select count(*) from public."user" where login = ?;
                """;
    }

    private String isUsernameAlreadyExistsSql() {
        return """
                select count(*) from public."user" where username = ?;
                """;
    }

    private String addUserSql() {
        return """
                insert into public."user" (login, password, username) values (?, ?, ?);
                """;
    }

    private String addUserRoleSql() {
        return """
                insert into public.user_role (user_id, role_id) values (?, ?);
                """;
    }
}
