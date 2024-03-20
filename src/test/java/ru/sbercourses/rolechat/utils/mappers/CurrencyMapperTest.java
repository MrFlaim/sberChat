package ru.sbercourses.rolechat.utils.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sbercourses.rolechat.dto.Currency;
import ru.sbercourses.rolechat.model.enums.CurrencyChar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Тест методов CurrencyMapper")
public class CurrencyMapperTest {

    @Test
    @DisplayName("Return 1 for RUB")
    public void testMapCurrencyResponse_RUB(){
        String string = "";
        String valute = CurrencyChar.RUB.name();
        CurrencyMapper currencyMapper = new CurrencyMapper();

        Currency result = currencyMapper.mapCurrencyResponse(string, valute);

        assertEquals(CurrencyChar.RUB.name(), result.getName());
        assertEquals(1L, result.getValue());
    }

    @Test
    @DisplayName("Return 9.2 for USD")
    public void testMapCurrencyResponse_Other() throws JsonProcessingException {
        String string = "{\"Valute\":{\"USD\":{ \"Name\": \"Доллар США\",\"Value\": 92.2,\"Nominal\":10}}}";
        String valute = "USD";
        CurrencyMapper currencyMapper = new CurrencyMapper();

        ObjectMapper objectMapper = mock(ObjectMapper.class);
        JsonNode valuteNode = mock(JsonNode.class);

        JsonNode jsonNode = mock(JsonNode.class);
        when(objectMapper.readTree(string)).thenReturn(jsonNode);
        when(jsonNode.get("Valute")).thenReturn(valuteNode);

        JsonNode usdNode = mock(JsonNode.class);
        when(valuteNode.get(valute)).thenReturn(usdNode);
        when(usdNode.get("Name")).thenReturn(mock(JsonNode.class));
        when(usdNode.get("Value")).thenReturn(mock(JsonNode.class));
        when(usdNode.get("Nominal")).thenReturn(mock(JsonNode.class));

        Currency result = currencyMapper.mapCurrencyResponse(string, valute);

        assertEquals("Доллар США", result.getName());
        assertEquals(9.2, result.getValue());
    }
}
