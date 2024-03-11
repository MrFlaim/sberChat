package ru.sbercourses.rolechat.sevice;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.sbercourses.rolechat.model.Message;

import java.util.List;

@Service
public interface MessageService {
    void addMessage(Message message);

    Message getMessageById(long id);

    List<Message> getAllMessageByChatId(long id);

    void updateMessage(Message message);

    void deleteMessageById(long id);
}
