package com.aurelian.application.controller;

import com.aurelian.application.CacheService;
import com.aurelian.application.entities.AccountDao;
import com.aurelian.application.repositories.AccountRepository;
import com.aurelian.application.services.ExchangeRateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private CacheService cacheService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
    private ExchangeRateService exchangeRateService;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
    private RestTemplate restTemplate;

	@Before
	public void init() {
		AccountDao accountDao1 = new AccountDao(1L,"RO00 RZBR 0000 0000 0000 0001", "RON", 10000L, Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        AccountDao accountDao2 = new AccountDao(2L, "RO00 RZBR 0000 0000 0000 0002", "USD", 5000L, Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(accountDao1));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(accountDao2));

        exchangeRateService.setRestTemplate(restTemplate);
    }

	@Test
	public void accountNotFound() throws Exception {
		mockMvc.perform(get("/account/6")).andExpect(status().isNotFound());
	}

    @Test
    public void accountFoundWithCachedRates() throws Exception {
        cacheService.evictAllCacheValues();

        mockMvc.perform(get("/account/2")).andExpect(status().is2xxSuccessful());
        exchangeRateService.getExchangeRateByCurrency("USD");

        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(Mockito.any(String.class), any());
    }

    @Test
    public void accountFoundWithoutCachedRates() throws Exception {
        cacheService.evictAllCacheValues();

	    mockMvc.perform(get("/account/1")).andExpect(status().is2xxSuccessful());
        cacheService.evictAllCacheValues();
        exchangeRateService.getExchangeRateByCurrency("RON");

        Mockito.verify(restTemplate, Mockito.times(2)).getForObject(Mockito.any(String.class), any());
	}
}