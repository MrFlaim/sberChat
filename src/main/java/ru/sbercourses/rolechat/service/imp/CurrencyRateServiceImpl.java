package ru.sbercourses.rolechat.service.imp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sbercourses.rolechat.dto.Currency;
import ru.sbercourses.rolechat.model.enums.CurrencyChar;
import ru.sbercourses.rolechat.service.CurrencyRateService;
import ru.sbercourses.rolechat.utils.mappers.CurrencyMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {
    public static final String CURRENCY_API = "https://www.cbr-xml-daily.ru/daily_json.js";

    RestTemplate restTemplate;

    CurrencyMapper currencyMapper;


    public CurrencyRateServiceImpl(CurrencyMapper currencyMapper) {
        this.restTemplate = new RestTemplate();
        this.currencyMapper = currencyMapper;
    }

    @Override
    public Currency getCurrencyRate(String valute) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(CURRENCY_API, String.class);
        currencyMapper = new CurrencyMapper();
        return currencyMapper.mapCurrencyResponse(responseEntity.getBody(), valute);
    }

    @Override
    public Map<CurrencyChar, Currency> getAllCurrencyRate() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(CURRENCY_API, String.class);
        currencyMapper = new CurrencyMapper();
        Map<CurrencyChar, Currency> allCurrency = new HashMap<>();
        for (CurrencyChar currencyChar : CurrencyChar.values()) {
            allCurrency.put(currencyChar,
                    currencyMapper.mapCurrencyResponse(responseEntity.getBody(), currencyChar.name()));
        }
        return allCurrency;
    }

    @Override
    public Map<CurrencyChar, Currency> getAllCurrencyRateWithChosenCurrencyChar(CurrencyChar currencyChar) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(CURRENCY_API, String.class);
        currencyMapper = new CurrencyMapper();
        Currency chosenCurrency = currencyMapper.mapCurrencyResponse(responseEntity.getBody(), currencyChar.name());
        Map<CurrencyChar, Currency> allCurrency = new HashMap<>();
        for (CurrencyChar allCurrencyChars : CurrencyChar.values()) {
            allCurrency.put(allCurrencyChars,
                    currencyMapper.mapCurrencyResponse(responseEntity.getBody(), allCurrencyChars.name()));
            allCurrency.get(allCurrencyChars).setValue(allCurrency.get(allCurrencyChars).getValue()
                    / chosenCurrency.getValue());
        }
        return allCurrency;
    }
}
