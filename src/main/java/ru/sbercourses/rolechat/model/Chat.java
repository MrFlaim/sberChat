package ru.sbercourses.rolechat.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chat_name", nullable = false)
    private String chatName;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;

    @ManyToMany(mappedBy = "chats", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    public Chat() {
    }

    public Chat(long id, String chatName, List<Message> messages, List<User> users) {
        this.id = id;
        this.chatName = chatName;
        this.messages = messages;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
