package com.aurelian.application;

import com.aurelian.application.entities.Account;
import com.aurelian.application.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication
@EnableCaching
//@EnableCircuitBreaker
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
            repository.save(new Account("RO00 RZBR 0000 0000 0000 0001", "RON", 10000, Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
            repository.save(new Account("RO00 RZBR 0000 0000 0000 0002", "USD", 5000, Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
            repository.save(new Account("RO00 RZBR 0000 0000 0000 0003", "CAD", 7000, Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
            repository.save(new Account("RO00 RZBR 0000 0000 0000 0004", "EUR", 3000, Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
            repository.save(new Account("RO00 RZBR 0000 0000 0000 0005", "RON", 11050, Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
        };
    }
}