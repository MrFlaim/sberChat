package ru.sbercourses.rolechat.configuration.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Тест методов SecurityConfiguration")
public class SecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Login page accessibility test")
    public void testLoginPageAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    @DisplayName("Register page accessibility test")
    public void testRegisterPageAccessible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    @DisplayName("Users page not accessibility test for USER")
    public void testUserPageAccessibleForUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"FAMILY_HEAD"})
    @DisplayName("Chats page not accessibility test for FAMILY_HEAD")
    public void testChatsPageAccessibleForFamilyHeadRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chats"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
