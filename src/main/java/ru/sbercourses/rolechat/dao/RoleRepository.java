package ru.sbercourses.rolechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercourses.rolechat.model.RoleEntity;
import ru.sbercourses.rolechat.model.enums.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> getRoleEntityByRole(Role role);
}
