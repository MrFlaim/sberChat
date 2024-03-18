package ru.sbercourses.rolechat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sbercourses.rolechat.model.Chat;
import ru.sbercourses.rolechat.model.Message;
import ru.sbercourses.rolechat.model.MoneyRequest;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.service.ChatService;
import ru.sbercourses.rolechat.service.MessageService;
import ru.sbercourses.rolechat.service.MoneyRequestService;
import ru.sbercourses.rolechat.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/chats")
public class ChatController {
    ChatService chatService;
    MessageService messageService;
    MoneyRequestService moneyRequestService;
    UserService userService;

    @Autowired
    public ChatController(ChatService chatService,
                          MessageService messageService,
                          MoneyRequestService moneyRequestService,
                          UserService userService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.moneyRequestService = moneyRequestService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllUserChats(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Chat> userChats = chatService.getAllUserChats(user.getId());
        model.addAttribute("chats", userChats);
        return "chats";
    }

    @PostMapping("/createChat")
    public String createChat(@RequestParam("chatName") String chatName, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Chat chat = new Chat();
        chat.setChatName(chatName);
        user.getChats().add(chat);
        chatService.addChat(chat);
        userService.updateUser(user);
        return "redirect:/chats";
    }

    @GetMapping("/{chatId}")
    public String getChat(@PathVariable("chatId") Long chatId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Chat chat = chatService.getChatById(chatId);
        if (user.getChats().stream().anyMatch(userChat -> userChat.getId() == chat.getId())) {
            List<MoneyRequest> moneyRequests = moneyRequestService.getAllMoneyRequestsByChatId(chatId);
            model.addAttribute("chat", chat);
            model.addAttribute("moneyRequests", moneyRequests);
            model.addAttribute("user", user);
            return "chat";
        } else {
            return "redirect:/chats";
        }
    }

    @PostMapping("/{chatId}/NewMessage")
    public String addMessageToChat(@PathVariable("chatId") long chatId,
                                   @RequestParam("userId") long userId,
                                   @RequestParam("text") String message) {
        messageService.writeMessage(userId, chatId, message);
        return "redirect:/chats/" + chatId;
    }

    @PostMapping("/{chatId}/messages/{messageId}")
    public String editMessage(@PathVariable("chatId") long chatId,
                              @PathVariable("messageId") long messageId,
                              @RequestParam("text") String newText,
                              Model model) {
        Message reWriteMessage = messageService.getMessageById(messageId);
        reWriteMessage.setMessage(newText + "(Редактированно)" + new Date());
        messageService.updateMessage(reWriteMessage);
        model.addAttribute("editMessage", reWriteMessage);
        return "redirect:/chats/" + chatId;
    }

    @GetMapping("/{chatId}/moneyRequests/{moneyRequestId}")
    public String getMoneyRequest(@PathVariable("moneyRequestId") long moneyRequestId,
                                  @PathVariable("chatId") long chatId,
                                  Model model,
                                  HttpSession session) {
        session.setAttribute("chatId", chatId);
        MoneyRequest moneyRequest = moneyRequestService.getMoneyRequestById(moneyRequestId);
        model.addAttribute("moneyRequest", moneyRequest);
        return "moneyRequest";
    }

    @PostMapping("/{chatId}/moneyRequests/{moneyRequestId}/addMoney")
    public String addMoneyToRequest(@PathVariable("moneyRequestId") long moneyRequestId,
                                    HttpSession session,
                                    @RequestParam("amount") long amount,
                                    Model model) {
        User user = (User) session.getAttribute("user");
        MoneyRequest moneyRequest = moneyRequestService.getMoneyRequestById(moneyRequestId);
        moneyRequest.getUsersWhoSendMoney().put(user, amount);
        long totalDonate = moneyRequest.getUsersWhoSendMoney().values().stream().reduce(Long::sum).orElse(0L);
        moneyRequest.setMoneyRequest(moneyRequest.getMoneyRequest() - totalDonate);
        moneyRequestService.updateMoneyRequest(moneyRequest);
        long chatId = (Long) session.getAttribute("chatId");
        session.removeAttribute("chatId");
        model.addAttribute("chatId", chatId);
        return "redirect:/chats/{chatId}/moneyRequests/{moneyRequestId}";
    }

}
