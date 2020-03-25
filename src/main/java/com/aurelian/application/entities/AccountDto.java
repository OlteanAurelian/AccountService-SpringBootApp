package com.aurelian.application.entities;

import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class AccountDto {
    private String iban;
    private String currency;
    private double balance;
    private Date lastUpdate;
}