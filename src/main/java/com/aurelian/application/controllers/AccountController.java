package com.aurelian.application.controllers;

import com.aurelian.application.entities.Account;
import com.aurelian.application.exceptions.AccountNotFoundException;
import com.aurelian.application.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.aurelian.application.repositories.AccountRepository;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/accounts")
    List<Account> findAll() {
        return repository.findAll();
    }

    @GetMapping("/account/{id}")
    String findOne(@PathVariable Long id) {
        Account account = repository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        double rate = exchangeRateService.getExchangeRateByCurrency(account.getCurrency()).getRate();

        return account.toString() + " --> balance amount in EUR: " + account.getBalance() / rate;
    }
}