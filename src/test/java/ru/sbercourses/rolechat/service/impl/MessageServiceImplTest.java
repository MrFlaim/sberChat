package ru.sbercourses.rolechat.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.sbercourses.rolechat.model.Message;
import ru.sbercourses.rolechat.model.exceptions.NoSuchMessageException;
import ru.sbercourses.rolechat.service.MessageService;
import ru.sbercourses.rolechat.service.imp.MessageServiceImpl;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест методов MessageServiceImplTests")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({MessageServiceImpl.class})
public class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @DisplayName("Get message by id")
    @Test
    public void shouldGetMessageFromDbById() {
        Message message = messageService.getMessageById(1);
        assertThat(message).hasFieldOrPropertyWithValue("message", "Hello, everyone!");
    }

    @DisplayName("Get all message by chat id")
    @Test
    public void shouldGetAllMessagesFromDbByChatId() {
        List<Message> messages = messageService.getAllMessageByChatId(2);
        assertThat(messages).hasSize(2);
    }

    @DisplayName("Update message and get")
    @Test
    public void shouldUpdateMessageAndGetItFromDb() {
        Message message = messageService.getMessageById(1);
        message.setMessage("Now");
        messageService.updateMessage(message);
        Message updateMessage = messageService.getMessageById(1);
        assertThat(updateMessage).hasFieldOrPropertyWithValue("message", "Now");
    }

    @DisplayName("Delete message and Get Exception")
    @Test
    public void shouldDeleteMessageFromDb() {
        messageService.deleteMessageById(1);
        assertThrows(NoSuchMessageException.class, () -> messageService.getMessageById(1));
    }

    @DisplayName("Add message and get")
    @Test
    public void shouldAddMessageAndGetItFromDb() {
        Message donorMessage = messageService.getMessageById(1);
        Message message = new Message();
        message.setMessage("New message");
        message.setChat(donorMessage.getChat());
        message.setCreateDate(new Date());
        message.setUser(donorMessage.getUser());
        messageService.addMessage(message);
        Message messageFromDb = messageService.getMessageById(message.getId());
        assertThat(messageFromDb).hasFieldOrPropertyWithValue("message", "New message");
    }
}