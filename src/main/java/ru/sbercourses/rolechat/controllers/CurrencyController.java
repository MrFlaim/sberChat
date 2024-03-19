package ru.sbercourses.rolechat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbercourses.rolechat.model.enums.CurrencyChar;
import ru.sbercourses.rolechat.service.CurrencyRateService;

@Controller
@RequestMapping("/currency")
public class CurrencyController {
    CurrencyRateService currencyRateService;

    @Autowired
    public CurrencyController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @GetMapping()
    public String getAllCurrencyList(Model model) {
        model.addAttribute("currencyMap", currencyRateService.getAllCurrencyRate());
        model.addAttribute("currentChar", "RUB");
        return "currency";
    }

    @PostMapping("/selectCurrentChar")
    public String getAllCurrencyListWithChosenCurrencyChar(@RequestParam("currentChar") CurrencyChar currency,
                                                           Model model) {
        model.addAttribute("currencyMap", currencyRateService.getAllCurrencyRateWithChosenCurrencyChar(currency));
        model.addAttribute("currentChar", currency);
        return "currency";
    }
}
