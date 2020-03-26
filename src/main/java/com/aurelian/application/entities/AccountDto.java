package com.aurelian.application.entities;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AccountDto {
    private String iban;
    private String currency;
    private double balance;
    private Date lastUpdate;
}