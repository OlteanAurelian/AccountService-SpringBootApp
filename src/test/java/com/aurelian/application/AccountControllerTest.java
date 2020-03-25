package com.aurelian.application;

import com.aurelian.application.entities.Account;
import com.aurelian.application.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountRepository accountRepository;

	@Before
	public void init() {
		Account account = new Account("RO00 RZBR 0000 0000 0000 0001", "RON", 10000, Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
	}

	@Test
	public void accountNotFound() throws Exception {
		mockMvc.perform(get("/account/6")).andExpect(status().isNotFound());
	}

    @Test
    public void accountFoundWithCachedRates() throws Exception {
        mockMvc.perform(get("/account/2")).andExpect(status().is2xxSuccessful());
    }
}