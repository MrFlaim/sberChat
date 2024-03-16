package ru.sbercourses.rolechat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sbercourses.rolechat.dao.UserRepository;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.model.exceptions.NoSuchUserException;
import ru.sbercourses.rolechat.service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        user.setOnlineStatus(false);
        userRepository.save(user);
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchUserException("User not found with id: " + userId));
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new NoSuchUserException("User not found with login: " + login));
    }

    @Override
    public List<User> getAllUserByChatId(long chatId) {
        return userRepository.findAllByChats_Id(chatId);
    }


    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
