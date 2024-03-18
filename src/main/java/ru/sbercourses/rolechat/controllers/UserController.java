package ru.sbercourses.rolechat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sbercourses.rolechat.model.Chat;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.service.ChatService;
import ru.sbercourses.rolechat.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public UserController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @GetMapping("")
    public String getAllUsersInApp(HttpSession session, Model model) {
        List<User> users = userService.getAllUsers();
        User sessionUser = (User) session.getAttribute("user");
        users.remove(sessionUser);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("sessionUser", sessionUser);
        return "users";
    }

    @PostMapping("/addToChat")
    public String addUserToChat(HttpSession session,
                                @RequestParam("userId") long userId,
                                @RequestParam("chatId") long chatId,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        User sessionUser = (User) session.getAttribute("user");
        User userToAdd = userService.getUserById(userId);
        Chat selectedChat = chatService.getChatById(chatId);
        if (!userToAdd.getChats().contains(selectedChat)) {
            List<Chat> userChats = userToAdd.getChats();
            userChats.add(selectedChat);
            userToAdd.setChats(userChats);
            userService.updateUser(userToAdd);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Пользователь уже состоит в выбранном чате");
        }
        model.addAttribute("user", sessionUser);
        return "redirect:/users";
    }

    @GetMapping("/chat")
    public String getAllUsersFromUserChats(HttpSession session, Model model) {
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
            return "usersInChat";
        }
        return "redirect:/login";
    }

    @PostMapping("/removeUserFromChat")
    public String removeUserFromChat(@RequestParam("userId") Long userId, @RequestParam("chatId") Long chatId) {
        User user = userService.getUserById(userId);
        user.getChats().removeIf(chat -> chat.getId() == chatId);
        userService.updateUser(user);
        return "redirect:/users/chat";
    }


}
