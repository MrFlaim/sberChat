package ru.sbercourses.rolechat.utils.mappers;

import ru.sbercourses.rolechat.dto.Currency;
import ru.sbercourses.rolechat.model.MoneyRequest;
import ru.sbercourses.rolechat.model.User;
import ru.sbercourses.rolechat.model.enums.CurrencyChar;
import ru.sbercourses.rolechat.service.CurrencyRateService;
import ru.sbercourses.rolechat.service.MoneyRequestService;

import java.util.Optional;
import java.util.Set;

public class MoneyRequestCalculator {

    public static MoneyRequest getMoneyRequestWithAddingMoney(MoneyRequestService moneyRequestService,
                                                              CurrencyRateService currencyRateService,
                                                              long moneyRequestId,
                                                              double amount,
                                                              CurrencyChar currency,
                                                              User user) {
        MoneyRequest moneyRequest = moneyRequestService.getMoneyRequestById(moneyRequestId);
        Currency userDonationCurrency = currencyRateService.getCurrencyRate(currency.name());
        Currency moneyRequestCurrency = currencyRateService.getCurrencyRate(moneyRequest.getCurrency().name());
        amount = (amount * userDonationCurrency.getValue() / moneyRequestCurrency.getValue());
        moneyRequestKeyResolver(moneyRequest, user, amount);
        moneyRequest.setMoneyRequest(moneyRequest.getMoneyRequest() - amount);
        return moneyRequest;
    }

    private static void moneyRequestKeyResolver(MoneyRequest moneyRequest, User user, double amount) {
        Set<User> usersWhoSendMoneyToMoneyRequest = moneyRequest.getUsersWhoSendMoney().keySet();
        User finalUser;
        Optional<User> userInSet = usersWhoSendMoneyToMoneyRequest.stream().
                filter(setUser -> setUser.getId() == user.getId()).findAny();
        finalUser = userInSet.orElse(user);
        moneyRequest.getUsersWhoSendMoney().merge(finalUser, amount, Double::sum);
    }
}
