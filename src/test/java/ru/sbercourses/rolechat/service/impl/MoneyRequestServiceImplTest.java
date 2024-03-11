package ru.sbercourses.rolechat.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.sbercourses.rolechat.model.MoneyRequest;
import ru.sbercourses.rolechat.model.exceptions.NoSuchMoneyRequestException;
import ru.sbercourses.rolechat.sevice.MoneyRequestService;
import ru.sbercourses.rolechat.sevice.imp.MoneyRequestServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест методов MoneyRequestServiceImplTests")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({MoneyRequestServiceImpl.class})
public class MoneyRequestServiceImplTest {

    @Autowired
    private MoneyRequestService moneyRequestService;

    @DisplayName("Get money request by id")
    @Test
    public void shouldGetMoneyRequestFromDbById() {
        MoneyRequest moneyRequest = moneyRequestService.getMoneyRequestById(1);
        assertThat(moneyRequest).hasFieldOrPropertyWithValue("moneyRequest", 100.0);
    }

    @DisplayName("Get all money requests by chat id")
    @Test
    public void shouldGetAllMoneyRequestsFromDbByChatId() {
        List<MoneyRequest> moneyRequests = moneyRequestService.getAllMoneyRequestsByChatId(1);
        assertThat(moneyRequests).hasSize(2);
    }

    @DisplayName("Update money request and get")
    @Test
    public void shouldUpdateMoneyRequestAndGetItFromDb() {
        MoneyRequest moneyRequest = moneyRequestService.getMoneyRequestById(1);
        moneyRequest.setMoneyRequest(200.0);
        moneyRequestService.updateMoneyRequest(moneyRequest);
        MoneyRequest updatedMoneyRequest = moneyRequestService.getMoneyRequestById(1);
        assertThat(updatedMoneyRequest).hasFieldOrPropertyWithValue("moneyRequest", 200.0);
    }

    @DisplayName("Delete money request and get exception")
    @Test
    public void shouldDeleteMoneyRequestFromDb() {
        moneyRequestService.deleteMoneyRequestById(1);
        assertThrows(NoSuchMoneyRequestException.class, () -> moneyRequestService.getMoneyRequestById(1));
    }

    @DisplayName("Add money request and get")
    @Test
    public void shouldAddMoneyRequestAndGetItFromDb() {
        MoneyRequest donorMoneyRequest = moneyRequestService.getMoneyRequestById(1);
        MoneyRequest moneyRequest = new MoneyRequest();
        moneyRequest.setMoneyRequest(300.0);
        moneyRequest.setChat(donorMoneyRequest.getChat());
        moneyRequest.setUserWhoNeedsMoney(donorMoneyRequest.getUserWhoNeedsMoney());
        moneyRequestService.addMoneyRequest(moneyRequest);
        MoneyRequest moneyRequestFromDb = moneyRequestService.getMoneyRequestById(moneyRequest.getId());
        assertThat(moneyRequestFromDb).hasFieldOrPropertyWithValue("moneyRequest", 300.0);
    }
}
