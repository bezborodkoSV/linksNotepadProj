package com.example.linksNotepad.service;

import com.example.linksNotepad.model.ExchangeRates;
import com.example.linksNotepad.repository.ExchangeRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ExchangeService {
    private final RestTemplate restTemplate;
    private final ExchangeRepository exchangeRepository;

    public ExchangeService(RestTemplate restTemplate, ExchangeRepository exchangeRepository) {
        this.restTemplate = restTemplate;
        this.exchangeRepository = exchangeRepository;
    }
private List<ExchangeRates> exchangeList=new ArrayList<>();

    public List<ExchangeRates> exchangeRates(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<ExchangeRates> entity=new HttpEntity<ExchangeRates>(httpHeaders);

        ArrayList<ExchangeRates> list = new ArrayList<>(Arrays.asList(restTemplate.exchange("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5",
                HttpMethod.GET,entity, ExchangeRates[].class).getBody()));
        for (ExchangeRates exchange:list) {
            saveOrUpdateExchangeRates(exchange);
        }
        return list;
    }

    private boolean saveOrUpdateExchangeRates(ExchangeRates exchange){
            if (exchangeRepository.findExchangeRatesByCcyAndBaseCcy(exchange.getCcy(), exchange.getBaseCcy()) == null) {
                ExchangeRates exchangeRates=new ExchangeRates(exchange.getCcy(), exchange.getBaseCcy(),
                        exchange.getBuy(), exchange.getSale());
                exchangeRepository.save(exchangeRates);
                return true;
            } else {
                ExchangeRates exchangeFromDb = exchangeRepository.findExchangeRatesByCcyAndBaseCcy(exchange.getCcy(), exchange.getBaseCcy());
                exchangeFromDb.setBuy(exchange.getBuy());
                exchangeFromDb.setSale(exchange.getSale());
                exchangeRepository.save(exchangeFromDb);
            }
            return true;
    }


}
