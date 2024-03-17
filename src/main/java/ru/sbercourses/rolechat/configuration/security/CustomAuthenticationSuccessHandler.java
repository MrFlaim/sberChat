package ru.sbercourses.rolechat.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.sbercourses.rolechat.dao.UserRepository;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.model.exceptions.NoSuchUserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public CustomAuthenticationSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException {
        if (authentication != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findByLogin(auth.getName())
                    .orElseThrow(() -> new NoSuchUserException("User not found with login: " + auth.getName()));
            user.setOnlineStatus(true);
            userRepository.save(user);
        }
        httpServletResponse.sendRedirect("/loginSuccess");
    }
}
