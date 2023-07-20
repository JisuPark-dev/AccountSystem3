package com.zerobase.account3.service;

import com.zerobase.account3.domain.Account;
import com.zerobase.account3.repository.AccountRepository;
import com.zerobase.account3.type.AccountStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.zerobase.account3.type.AccountStatus.IN_USE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("계좌 조회 성공")
    void testXXX(){
        //given
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(
                        Account.builder()
                                .accountNumber("1000000000")
                                .accountStatus(IN_USE)
                                .build())
                );

        //long타입의 박스 생성
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        Account account = accountService.getAccount(4555L);
        //then
        verify(accountRepository, times(1)).findById(captor.capture());
        verify(accountRepository, times(0)).save(any());
        assertEquals(4555L, captor.getValue());
        assertEquals(account.getAccountNumber(), "1000000000");
    }
    
    @Test
    void testGetAccount(){
        //given
        Account account = accountService.getAccount(1L);
        //when
        //then
        assertEquals("1000000000", account.getAccountNumber());
        assertEquals(account.getAccountStatus(), IN_USE);
    }
}