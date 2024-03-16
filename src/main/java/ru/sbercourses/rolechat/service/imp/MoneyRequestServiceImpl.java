package ru.sbercourses.rolechat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sbercourses.rolechat.dao.MoneyRequestRepository;
import ru.sbercourses.rolechat.model.MoneyRequest;
import ru.sbercourses.rolechat.model.exceptions.NoSuchMoneyRequestException;
import ru.sbercourses.rolechat.service.MoneyRequestService;

import java.util.List;

public class MoneyRequestServiceImpl implements MoneyRequestService {

    private final MoneyRequestRepository moneyRequestRepository;

    @Autowired
    public MoneyRequestServiceImpl(MoneyRequestRepository moneyRequestRepository) {
        this.moneyRequestRepository = moneyRequestRepository;
    }

    @Override
    public void addMoneyRequest(MoneyRequest moneyRequest) {
        moneyRequestRepository.save(moneyRequest);
    }

    @Override
    public MoneyRequest getMoneyRequestById(long id) {
        return moneyRequestRepository.findById(id)
                .orElseThrow(() -> new NoSuchMoneyRequestException("MoneyRequest not found with id: " + id));
    }

    @Override
    public List<MoneyRequest> getAllMoneyRequestsByChatId(long chatId) {
        return moneyRequestRepository.findAllByChatId(chatId);
    }

    @Override
    public void updateMoneyRequest(MoneyRequest moneyRequest) {
        moneyRequestRepository.save(moneyRequest);
    }

    @Override
    public void deleteMoneyRequestById(long id) {
        moneyRequestRepository.deleteById(id);
    }
}
