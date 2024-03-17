package ru.sbercourses.rolechat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercourses.rolechat.dto.UserDto;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.service.UserService;
import ru.sbercourses.rolechat.utils.mappers.UserMapper;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(HttpSession session, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByLogin(auth.getName());
        session.setAttribute("user", userService.getUserByLogin(auth.getName()));
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/register")
    public String registrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDto userDto) {
        userDto.setPassword(userDto.getPassword());
        userService.addUser(UserMapper.toUser(userDto));
        return "redirect:/login";
    }
}
