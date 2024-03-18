package ru.sbercourses.rolechat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbercourses.rolechat.dao.ChatRepository;
import ru.sbercourses.rolechat.dao.MessageRepository;
import ru.sbercourses.rolechat.dao.UserRepository;
import ru.sbercourses.rolechat.model.Chat;
import ru.sbercourses.rolechat.model.Message;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.model.exceptions.NoSuchChatException;
import ru.sbercourses.rolechat.model.exceptions.NoSuchMessageException;
import ru.sbercourses.rolechat.model.exceptions.NoSuchUserException;
import ru.sbercourses.rolechat.service.MessageService;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;


    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }

    @Override
    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Message getMessageById(long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new NoSuchMessageException("Message not found with id: " + id));
    }

    @Override
    public List<Message> getAllMessageByChatId(long chatId) {
        return messageRepository.findAllByChatId(chatId);
    }

    @Override
    public void updateMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void deleteMessageById(long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void writeMessage(long userId, long chatId, String messageText) {
        Message message = new Message();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchUserException("User not found with id: " + userId));
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new NoSuchChatException("User not found with id: " + chatId));
        message.setUser(user);
        message.setChat(chat);
        message.setMessage(messageText);
        message.setCreateDate(new Date());
        messageRepository.save(message);
    }

}