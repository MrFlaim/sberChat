package ru.sbercourses.rolechat.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.sbercourses.rolechat.model.RoleEntity;
import ru.sbercourses.rolechat.model.enums.Role;
import ru.sbercourses.rolechat.model.exceptions.NoSuchRoleException;
import ru.sbercourses.rolechat.service.RoleService;
import ru.sbercourses.rolechat.service.imp.RoleServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

;

@DisplayName("Тест методов RoleServiceImpl")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({RoleServiceImpl.class})
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @DisplayName("Get role by user id")
    @Test
    public void shouldGetRoleByUserId() {
        RoleEntity role = roleService.getRoleEntityByUserId(1);
        assertThat(role.getRole()).isEqualTo(Role.USER);
    }
}
