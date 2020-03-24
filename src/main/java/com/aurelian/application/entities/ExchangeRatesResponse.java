package com.aurelian.application.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExchangeRatesResponse {
    private Map<String, Double> rates;
    private String base;
    private String date;

    public ExchangeRatesResponse(Map<String, Double> rates, String base, String date) {
        this.rates = rates;
        this.base = base;
        this.date = date;
    }
}