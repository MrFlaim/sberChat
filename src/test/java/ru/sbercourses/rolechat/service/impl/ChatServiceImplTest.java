package ru.sbercourses.rolechat.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.sbercourses.rolechat.model.Chat;
import ru.sbercourses.rolechat.model.exceptions.NoSuchChatException;
import ru.sbercourses.rolechat.sevice.ChatService;
import ru.sbercourses.rolechat.sevice.imp.ChatServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест методов ChatServiceImpl")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ChatServiceImpl.class)
public class ChatServiceImplTest {
    @Autowired
    private ChatService chatService;

    @DisplayName("Get chat by id")
    @Test
    public void shouldGetChatFromDbById() {
        Chat chat = chatService.getChatById(1);
        assertThat(chat).hasFieldOrPropertyWithValue("chatName", "General Chat");
    }

    @DisplayName("Get all user chats")
    @Test
    public void shouldGetAllUserChatsFromDb() {
        long userId = 1;
        List<Chat> chats = chatService.getAllUserChats(userId);
        assertThat(chats).hasSize(2);
    }

    @DisplayName("Update chat and get")
    @Test
    public void shouldUpdateChatAndGetItFromDb() {
        Chat chat = chatService.getChatById(1);
        chat.setChatName("UpdatedChatName");
        chatService.updateChat(chat);
        Chat updatedChat = chatService.getChatById(1);
        assertThat(updatedChat).hasFieldOrPropertyWithValue("chatName", "UpdatedChatName");
    }

    @DisplayName("Delete chat and get exception")
    @Test
    public void shouldDeleteChatFromDb() {
        chatService.deleteChatById(1);
        assertThrows(NoSuchChatException.class, () -> chatService.getChatById(1));
    }

    @DisplayName("Add chat and get")
    @Test
    public void shouldAddChatAndGetItFromDb() {
        Chat chat = new Chat();
        chat.setChatName("NewChat");
        chatService.addChat(chat);
        Chat chatFromDb = chatService.getChatById(chat.getId());
        assertThat(chatFromDb).hasFieldOrPropertyWithValue("chatName", "NewChat");
    }
}