package com.aurelian.application.services;

import com.aurelian.application.entities.AccountDao;
import com.aurelian.application.entities.AccountDto;
import com.aurelian.application.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream().map(AccountTransformer::from).collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDto> findById(long id) {
        Optional<AccountDao> accountDao = accountRepository.findById(id);
        return accountDao.map(AccountTransformer::from);
    }
}