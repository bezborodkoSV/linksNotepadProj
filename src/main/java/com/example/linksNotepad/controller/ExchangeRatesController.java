package com.example.linksNotepad.controller;

import com.example.linksNotepad.model.ExchangeRates;
import com.example.linksNotepad.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExchangeRatesController {
    private final ExchangeService exchangeService;

    public ExchangeRatesController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/exchange")
    public String exchange(Model model){
    model.addAttribute("exchangeList",exchangeService.exchangeRates());
    return "exchange";
}

}
