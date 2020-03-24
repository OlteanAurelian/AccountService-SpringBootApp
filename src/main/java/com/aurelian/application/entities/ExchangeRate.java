package com.aurelian.application.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExchangeRate {
    private String currency;
    private double rate;

    public ExchangeRate(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }
}