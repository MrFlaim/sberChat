package ru.sbercourses.rolechat.utils.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.sbercourses.rolechat.dto.Currency;
import ru.sbercourses.rolechat.model.enums.CurrencyChar;

@Service
public class CurrencyMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Currency mapCurrencyResponse(String string, String valute) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(string);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (valute.equals(CurrencyChar.RUB.name())) {
            return new Currency(CurrencyChar.RUB.name(), 1L);
        }
        return new Currency(jsonNode.get("Valute").get(valute).get("Name").asText(),
                (double) jsonNode.get("Valute").get(valute).get("Value").asLong() /
                        jsonNode.get("Valute").get(valute).get("Nominal").asLong());
    }
}
