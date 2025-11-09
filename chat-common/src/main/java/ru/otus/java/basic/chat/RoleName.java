package ru.otus.java.basic.chat;

public enum RoleName {
    USER("user"), MANAGER("manager"), ADMIN("admin");

    private final String sqlName;

    RoleName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String toSqlName() {
        return sqlName;
    }

    public static RoleName fromString(String roleName) {
        if (roleName == null || roleName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя роли не может быть пустым");
        }
        return RoleName.valueOf(roleName.trim().toUpperCase());
    }
}
