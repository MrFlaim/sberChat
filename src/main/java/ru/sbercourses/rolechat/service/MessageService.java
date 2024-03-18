package ru.sbercourses.rolechat.service;

import ru.sbercourses.rolechat.model.Message;

import java.util.List;

public interface MessageService {
    void addMessage(Message message);

    Message getMessageById(long id);

    List<Message> getAllMessageByChatId(long id);

    void updateMessage(Message message);

    void deleteMessageById(long id);

    void writeMessage(long userId, long chatId, String messageText);
}
