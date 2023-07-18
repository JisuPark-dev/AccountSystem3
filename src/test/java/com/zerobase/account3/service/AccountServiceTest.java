package com.zerobase.account3.service;

import com.zerobase.account3.domain.Account;
import com.zerobase.account3.type.AccountStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @BeforeEach
    void init() {
        accountService.createAccount();
    }

    @Test
    void testGetAccount(){
        //given
        Account account = accountService.getAccount(1L);
        //when
        //then
        assertEquals("1000000000", account.getAccountNumber());
        assertEquals(account.getAccountStatus(), AccountStatus.IN_USE);
    }
}