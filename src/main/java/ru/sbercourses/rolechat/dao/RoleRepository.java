package ru.sbercourses.rolechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercourses.rolechat.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
