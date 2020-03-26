package com.aurelian.application.entities;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeRatesResponse {
    private Map<String, Double> rates;
    private String base;
    private String date;
}