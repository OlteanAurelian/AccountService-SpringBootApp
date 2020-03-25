package com.aurelian.application.services;

import com.aurelian.application.entities.AccountDao;
import com.aurelian.application.entities.AccountDto;

public class AccountTransformer {
    public static AccountDto from(AccountDao accountDao) {
        return new AccountDto(accountDao.getIban(), accountDao.getCurrency(), accountDao.getBalance(), accountDao.getLastUpdate());
    }
}