package ru.sbercourses.rolechat.service;

import ru.sbercourses.rolechat.model.RoleEntity;
import ru.sbercourses.rolechat.model.enums.Role;

public interface RoleService {

    RoleEntity getRoleEntityByUserId(long userId);

    void deleteEntityById(long roleId);

    void addRole(RoleEntity role);

    RoleEntity getRoleByRoleName(Role role);
}
