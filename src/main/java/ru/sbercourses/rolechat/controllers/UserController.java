package ru.sbercourses.rolechat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sbercourses.rolechat.model.Chat;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(HttpSession session, Model model) {
        if (session != null) {
            User user = (User) session.getAttribute("user");
            List<Chat> chats = user.getChats();
            List<User> users = new ArrayList<>();
            for (Chat chat : chats) {
                users.addAll(userService.getAllUserByChatId(chat.getId()));
            }
            model.addAttribute("users", users);
            return "users";
        }
        return "redirect:/login";
    }
}
