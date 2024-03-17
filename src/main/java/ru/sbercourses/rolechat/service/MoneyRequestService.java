package ru.sbercourses.rolechat.service;

import ru.sbercourses.rolechat.model.MoneyRequest;

import java.util.List;

public interface MoneyRequestService {
    void addMoneyRequest(MoneyRequest moneyRequest);

    MoneyRequest getMoneyRequestById(long id);

    List<MoneyRequest> getAllMoneyRequestsByChatId(long id);

    void updateMoneyRequest(MoneyRequest moneyRequest);

    void deleteMoneyRequestById(long id);
}
