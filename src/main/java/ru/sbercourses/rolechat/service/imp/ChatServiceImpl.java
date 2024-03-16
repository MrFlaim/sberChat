package ru.sbercourses.rolechat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sbercourses.rolechat.dao.ChatRepository;
import ru.sbercourses.rolechat.model.Chat;
import ru.sbercourses.rolechat.model.exceptions.NoSuchChatException;
import ru.sbercourses.rolechat.service.ChatService;

import java.util.List;

public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public void addChat(Chat chat) {
        chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(long id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new NoSuchChatException("User not found with id: " + id));
    }

    @Override
    public List<Chat> getAllUserChats(long userId) {
        return chatRepository.findAllByUserId(userId);
    }

    @Override
    public void updateChat(Chat chat) {
        chatRepository.save(chat);
    }

    @Override
    public void deleteChatById(long id) {
        chatRepository.deleteById(id);
    }
}
