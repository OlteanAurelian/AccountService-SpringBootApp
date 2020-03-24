package com.aurelian.application.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String iban;
    private String currency;
    private int balance;
    private Date lastUpdate;

    public Account(String iban, String currency, int balance, Date lastUpdate) {
        this.iban = iban;
        this.currency = currency;
        this.balance = balance;
        this.lastUpdate = lastUpdate;
    }
}