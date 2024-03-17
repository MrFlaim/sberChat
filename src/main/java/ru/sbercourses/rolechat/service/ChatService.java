package ru.sbercourses.rolechat.service;

import ru.sbercourses.rolechat.model.Chat;

import java.util.List;

public interface ChatService {
    void addChat(Chat chat);

    Chat getChatById(long id);

    List<Chat> getAllUserChats(long userId);

    void updateChat(Chat chat);

    void deleteChatById(long id);
}
