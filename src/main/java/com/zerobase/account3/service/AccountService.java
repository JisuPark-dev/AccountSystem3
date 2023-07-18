package com.zerobase.account3.service;

import com.zerobase.account3.domain.Account;
import com.zerobase.account3.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.zerobase.account3.type.AccountStatus.IN_USE;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public void createAccount() {
        accountRepository.save(
                Account.builder()
                        .accountNumber("1000000000")
                        .accountStatus(IN_USE)
                        .build()
        );
    }

    @Transactional
    public Account getAccount(Long id) {
        return accountRepository.findById(id).get();
    }

}
