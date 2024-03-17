package ru.sbercourses.rolechat.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "t_money_request")
public class MoneyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @Column(name = "money_request", nullable = false)
    private double moneyRequest;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userWhoNeedsMoney;

    @ElementCollection
    @CollectionTable(name = "t_user_to_money_request", joinColumns = @JoinColumn(name = "money_request_id"))
    @MapKeyJoinColumn(name = "user_id")
    @Column(name = "money_send")
    private Map<User, Integer> usersWhoSendMoney;


    public MoneyRequest() {
    }

    public MoneyRequest(long id, Chat chat, double moneyRequest, User userWhoNeedsMoney, Map<User, Integer> usersWhoSendMoney) {
        this.id = id;
        this.chat = chat;
        this.moneyRequest = moneyRequest;
        this.userWhoNeedsMoney = userWhoNeedsMoney;
        this.usersWhoSendMoney = usersWhoSendMoney;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public double getMoneyRequest() {
        return moneyRequest;
    }

    public void setMoneyRequest(double moneyRequest) {
        this.moneyRequest = moneyRequest;
    }

    public User getUserWhoNeedsMoney() {
        return userWhoNeedsMoney;
    }

    public void setUserWhoNeedsMoney(User userWhoNeedsMoney) {
        this.userWhoNeedsMoney = userWhoNeedsMoney;
    }

    public Map<User, Integer> getUsersWhoSendMoney() {
        return usersWhoSendMoney;
    }

    public void setUsersWhoSendMoney(Map<User, Integer> usersWhoSendMoney) {
        this.usersWhoSendMoney = usersWhoSendMoney;
    }
}
