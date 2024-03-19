package ru.sbercourses.rolechat.service;

import ru.sbercourses.rolechat.dto.Currency;
import ru.sbercourses.rolechat.model.enums.CurrencyChar;

import java.util.Map;

public interface CurrencyRateService {

    Currency getCurrencyRate(String valute);

    Map<CurrencyChar, Currency> getAllCurrencyRate();

    Map<CurrencyChar, Currency> getAllCurrencyRateWithChosenCurrencyChar(CurrencyChar currencyChar);
}
