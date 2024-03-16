package ru.sbercourses.rolechat.service;

import org.springframework.stereotype.Service;
import ru.sbercourses.rolechat.model.User;

import java.util.List;

@Service
public interface UserService {
    void addUser(User user);

    User getUserById(long id);

    User getUserByLogin(String login);

    List<User> getAllUserByChatId(long chatId);

    void updateUser(User user);

    void deleteUserById(long id);
}
