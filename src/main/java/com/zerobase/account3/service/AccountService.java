package com.zerobase.account3.service;

import com.zerobase.account3.domain.Account;
import com.zerobase.account3.domain.AccountUser;
import com.zerobase.account3.dto.AccountDto;
import com.zerobase.account3.exception.AccountException;
import com.zerobase.account3.repository.AccountRepository;
import com.zerobase.account3.repository.AccountUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.zerobase.account3.dto.AccountDto.fromEntity;
import static com.zerobase.account3.type.AccountStatus.IN_USE;
import static com.zerobase.account3.type.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;

    /**
     * 사용자가 있는지 확인
     * 계좌번호 생성
     * 계좌를 저장하고, 그 정보를 넘긴다.
     */
    @Transactional
    public AccountDto createAccount(Long userId, Long initialBalance) {
        //사용자가 있는지 확인
        AccountUser accountUser = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(USER_NOT_FOUND));
        //계좌번호 생성
        String newAccountNumber = accountRepository.findFirstByOrderByIdDesc()
                .map(account ->
                        (Integer.parseInt(account.getAccountNumber())) + 1 + ""
                )
                .orElse("1000000000");
        //계좌를 저장하고, 그 정보를 넘긴다.
        return fromEntity(accountRepository.save(
                Account.builder()
                        .accountUser(accountUser)
                        .accountNumber(newAccountNumber)
                        .accountStatus(IN_USE)
                        .balance(initialBalance)
                        .registeredAt(LocalDateTime.now())
                        .build()
                )
        );
    }

    @Transactional
    public Account getAccount(Long id) {
        return accountRepository.findById(id).get();
    }

}
