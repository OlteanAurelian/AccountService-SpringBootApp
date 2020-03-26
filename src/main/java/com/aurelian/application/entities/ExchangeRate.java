package com.aurelian.application.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeRate {
    private String currency;
    private double rate;
}