package com.aurelian.application.controllers;

import com.aurelian.application.entities.AccountDto;
import com.aurelian.application.exceptions.AccountNotFoundException;
import com.aurelian.application.services.AccountService;
import com.aurelian.application.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/accounts")
    List<AccountDto> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/account/{id}")
    String findOne(@PathVariable Long id) {
        AccountDto accountDto = accountService.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        double rate = exchangeRateService.getExchangeRateByCurrency(accountDto.getCurrency()).getRate();

        return accountDto.toString() + " --> balance amount in EUR: " + accountDto.getBalance() / rate;
    }
}