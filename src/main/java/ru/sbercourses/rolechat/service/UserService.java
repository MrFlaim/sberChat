package ru.sbercourses.rolechat.service;

import ru.sbercourses.rolechat.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    User getUserById(long id);

    User getUserByLogin(String login);

    List<User> getAllUserByChatId(long chatId);

    List<User> getAllUserByChatIdAndRoleId(long chatId, long roleId);

    void updateUser(User user);

    void deleteUserById(long id);
}
