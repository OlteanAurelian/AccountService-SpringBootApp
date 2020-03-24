package com.aurelian.application.services;

import com.aurelian.application.entities.ExchangeRate;
import com.aurelian.application.entities.ExchangeRatesResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ExchangeRateService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    @Cacheable("exchangeRate")
    public ExchangeRate getExchangeRateByCurrency(String currency) {
        System.out.println("Going to the rates-ws for exchange rates for currency: " + currency);
        String url = "https://api.exchangeratesapi.io/latest";

        ExchangeRatesResponse response = restTemplate.getForObject(url, ExchangeRatesResponse.class);
        System.out.println(response);

        return new ExchangeRate(currency, Objects.requireNonNull(response).getRates().get(currency));
    }

    public ExchangeRate fallback(String currency, Throwable hystrixCommand){
        System.out.println("Enter fallback for currency: " + currency);
        return new ExchangeRate(currency, 1);
    }
}