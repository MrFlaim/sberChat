package ru.sbercourses.rolechat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbercourses.rolechat.dao.UserRepository;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.model.exceptions.NoSuchUserException;
import ru.sbercourses.rolechat.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        user.setOnlineStatus(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchUserException("User not found with id: " + userId));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new NoSuchUserException("User not found with login: " + login));
    }

    @Override
    public List<User> getAllUserByChatId(long chatId) {
        return userRepository.findAllByChats_Id(chatId);
    }

    @Override
    public List<User> getAllUserByChatIdAndRoleId(long chatId, long roleId) {
        return userRepository.findAllByChats_IdAndRolesId(chatId, roleId);
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
