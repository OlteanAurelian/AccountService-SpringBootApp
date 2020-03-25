package com.aurelian.application.controller;

import com.aurelian.application.entities.AccountDao;
import com.aurelian.application.entities.ExchangeRatesResponse;
import com.aurelian.application.repositories.AccountRepository;
import com.aurelian.application.services.ExchangeRateService;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerIntegrationTest {
    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepository;

    private Date commonDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    @Before
    public void init() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("RON", 4.85);

        ExchangeRatesResponse exchangeRatesResponse = new ExchangeRatesResponse();
        exchangeRatesResponse.setRates(rates);

        RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
        String url = "https://api.exchangeratesapi.io/latest";

        Mockito.when(mockRestTemplate.getForObject(url, ExchangeRatesResponse.class)).thenReturn(exchangeRatesResponse);
        exchangeRateService.setRestTemplate(mockRestTemplate);

        AccountDao accountDao = new AccountDao(10L,"RO00 RZBR 0000 0000 0000 0001", "RON", 10000L, commonDate);
        when(accountRepository.findById(10L)).thenReturn(Optional.of(accountDao));
    }

    @Test
    public void accountFoundWithCachedRates() throws Exception {
        String expected = "AccountDto(iban=RO00 RZBR 0000 0000 0000 0001, currency=RON, balance=10000.0, lastUpdate=" + commonDate + ") --> balance amount in EUR: 2061.855670103093";
        MvcResult result = mockMvc.perform(get("/account/10")).andExpect(status().is2xxSuccessful()).andReturn();
        String received = result.getResponse().getContentAsString();

        Assert.assertEquals(received, expected);
    }
}