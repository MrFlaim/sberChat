package ru.sbercourses.rolechat.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sbercourses.rolechat.model.RoleEntity;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.model.enums.Role;
import ru.sbercourses.rolechat.model.exceptions.NoSuchUserException;
import ru.sbercourses.rolechat.service.UserService;
import ru.sbercourses.rolechat.service.imp.UserServiceImpl;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Тест методов UserServiceImplTests")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({UserServiceImpl.class})
public class UserServiceImplTests {
    @Autowired
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @DisplayName("Get user by id")
    @Test
    public void shouldGetUserFromDbById() {
        User user = userService.getUserById(1);
        assertThat(user).hasFieldOrPropertyWithValue("name", "John");
    }

    @DisplayName("Get user by chatId and RoleId")
    @Test
    public void shouldGetUserFromDbByChatIdAndRoleId() {
        List<User> users = userService.getAllUserByChatIdAndRoleId(1, 1);
        assertThat(users).hasSize(2);
    }

    @DisplayName("Get user by login")
    @Test
    public void shouldGetUserFromDbByLogin() {
        User user = userService.getUserByLogin("user1");
        assertThat(user).hasFieldOrPropertyWithValue("name", "John");
    }

    @DisplayName("Get all users by Chat id")
    @Test
    public void shouldGetAllUserFromDbByChatId() {
        List<User> users = userService.getAllUserByChatId(1);
        assertThat(users).hasSize(2);
    }

    @DisplayName("Update user and get")
    @Test
    public void shouldUpdateUserAndGetHimFromDb() {
        User user = userService.getUserById(1);
        user.setName("Johnny");
        userService.updateUser(user);
        User updatedUser = userService.getUserById(1);
        assertThat(updatedUser).hasFieldOrPropertyWithValue("name", "Johnny");
    }

    @DisplayName("Delete user and Get Exception")
    @Test
    public void shouldDeleteUserFromDb() {
        userService.deleteUserById(1);
        assertThrows(NoSuchUserException.class, () -> userService.getUserById(1));
    }

    @DisplayName("Add user and get")
    @Test
    public void shouldAddUserAndGetHimFromDb() {
        User user = new User();
        RoleEntity role = new RoleEntity();
        role.setRole(Role.USER);
        user.setLogin("newuser");
        user.setPassword("newpassword");
        when(passwordEncoder.encode("newpassword")).thenReturn("EncodePassword");
        user.setRoles(Set.of(role));
        user.setName("New");
        user.setSurname("User");
        user.setPhoneNumber("9106280056");
        user.setOnlineStatus(false);
        userService.addUser(user);
        User userFromDb = userService.getUserByLogin("newuser");
        assertThat(userFromDb).hasFieldOrPropertyWithValue("name", "New");
    }
}