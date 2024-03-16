package ru.sbercourses.rolechat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sbercourses.rolechat.dao.MessageRepository;
import ru.sbercourses.rolechat.model.Message;
import ru.sbercourses.rolechat.model.exceptions.NoSuchMessageException;
import ru.sbercourses.rolechat.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
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
}