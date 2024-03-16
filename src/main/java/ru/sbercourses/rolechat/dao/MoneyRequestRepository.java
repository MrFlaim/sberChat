package ru.sbercourses.rolechat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbercourses.rolechat.model.MoneyRequest;

import java.util.List;

public interface MoneyRequestRepository extends JpaRepository<MoneyRequest, Long> {
    List<MoneyRequest> findAllByChatId(long chatId);
}
