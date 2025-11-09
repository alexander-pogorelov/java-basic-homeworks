package ru.otus.java.basic.chat.service;

import ru.otus.java.basic.chat.Role;
import ru.otus.java.basic.chat.RoleName;

public interface RoleService {
    Role getRoleByName(RoleName roleName);
}
