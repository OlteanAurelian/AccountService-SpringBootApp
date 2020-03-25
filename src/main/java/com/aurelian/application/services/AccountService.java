package com.aurelian.application.services;

import com.aurelian.application.entities.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public List<AccountDto> findAll();
    public Optional<AccountDto> findById(long id);
}