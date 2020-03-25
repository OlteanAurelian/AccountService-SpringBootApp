package com.aurelian.application.services;

import com.aurelian.application.entities.ExchangeRate;
import com.aurelian.application.entities.ExchangeRatesResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Log4j2
@Service
public class ExchangeRateService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    @Cacheable("exchangeRate")
    public ExchangeRate getExchangeRateByCurrency(String currency) {
        log.info("Going to the rates-ws for exchange rates for currency {}", currency);
        String url = "https://api.exchangeratesapi.io/latest";
        ExchangeRatesResponse response = restTemplate.getForObject(url, ExchangeRatesResponse.class);
        log.info("ExchangeRatesResponse {}", response);

        return new ExchangeRate(currency, Objects.requireNonNull(response).getRates().get(currency));
    }

    public ExchangeRate fallback(String currency, Throwable hystrixCommand){
        log.info("Enter fallback for currency {}", currency);
        return new ExchangeRate(currency, 1);
    }
}