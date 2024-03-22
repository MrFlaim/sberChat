package ru.sbercourses.rolechat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercourses.rolechat.dto.UserDto;
import ru.sbercourses.rolechat.model.RoleEntity;
import ru.sbercourses.rolechat.model.exceptions.PhonenumberNotUniqueException;
import ru.sbercourses.rolechat.model.exceptions.UsernameNotUniqueException;
import ru.sbercourses.rolechat.service.RoleService;
import ru.sbercourses.rolechat.service.UserService;
import ru.sbercourses.rolechat.utils.mappers.UserMapper;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("user", userService.getUserByLogin(auth.getName()));
        session.setAttribute("roles", userService.getUserByLogin(auth.getName())
                .getRoles().stream().map(roleEntity -> roleEntity.getRole().name()).collect(Collectors.toSet()));
        return "redirect:/chats";
    }

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute(new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid UserDto userDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(userDto);
            return "register";
        } else {
            userDto.setPassword(userDto.getPassword());
            RoleEntity role = roleService.getRoleByRoleName(userDto.getRole());
            try {
                userService.addUser(UserMapper.toUser(userDto, role));
                return "redirect:/login";
            } catch (UsernameNotUniqueException ex) {
                model.addAttribute("usernameError", "Username is already taken");
                return "register";
            } catch (PhonenumberNotUniqueException ex) {
                model.addAttribute("phoneNumberError", "Phone number is already registered");
                return "register";
            }
        }
    }
}
