package ru.sbercourses.rolechat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbercourses.rolechat.model.Chat;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllUsers(HttpSession session, Model model) {
        if (session != null) {
            User user = (User) session.getAttribute("user");
            List<Chat> chats = user.getChats();
            HashSet<User> users = new HashSet<>();
            for (Chat chat : chats) {
                users.addAll(userService.getAllUserByChatId(chat.getId()));
            }
            for (User userDb : users) {
                userDb.getChats().removeIf(userChat -> !userChat.getUsers().contains(userDb));
            }
            model.addAttribute("users", users);
            return "users";
        }
        return "redirect:/login";
    }

    @PostMapping("/removeUserFromChat")
    public String removeUserFromChat(@RequestParam("userId") Long userId, @RequestParam("chatId") Long chatId) {
        User user = userService.getUserById(userId);
        user.getChats().removeIf(chat -> chat.getId() == chatId);
        userService.updateUser(user);
        return "redirect:/users";
    }
}
