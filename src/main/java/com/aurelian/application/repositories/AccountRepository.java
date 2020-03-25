package com.aurelian.application.repositories;

import com.aurelian.application.entities.AccountDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDao, Long> {
}