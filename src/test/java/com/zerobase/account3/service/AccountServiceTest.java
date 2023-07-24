package com.zerobase.account3.service;

import com.zerobase.account3.domain.Account;
import com.zerobase.account3.domain.AccountUser;
import com.zerobase.account3.dto.AccountDto;
import com.zerobase.account3.repository.AccountRepository;
import com.zerobase.account3.repository.AccountUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountUserRepository accountUserRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    void createAccountSuccess(){
        //given
        AccountUser user = AccountUser.builder()
                .id(2L)
                .name("user1")
                .build();
        given(accountUserRepository.findById(anyLong()))
                .willReturn(Optional.of(user));
        given(accountRepository.findFirstByOrderByIdDesc())
                .willReturn(Optional.of(Account.builder()
                        .accountNumber("1000000012")
                        .build()));
        given(accountRepository.save(any()))
                .willReturn(Account.builder()
                        .accountUser(user)
                        .accountNumber("1000000013")
                        .build());
        //when
        AccountDto accountDto = accountService.createAccount(2L, 1000L);
        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        //then
        verify(accountRepository, times(1)).save(captor.capture());
        assertEquals(2L,accountDto.getUserId());
        assertEquals("1000000013",captor.getValue().getAccountNumber());
    }
}