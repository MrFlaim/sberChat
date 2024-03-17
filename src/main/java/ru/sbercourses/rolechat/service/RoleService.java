package ru.sbercourses.rolechat.service;

import ru.sbercourses.rolechat.model.RoleEntity;

public interface RoleService {

    RoleEntity getRoleEntityByUserId(long userId);

    void deleteEntityById(long roleId);

    void addRole(RoleEntity role);
}
