package com.zerobase.account3.controller;

import com.zerobase.account3.domain.Account;
import com.zerobase.account3.service.AccountService;
import com.zerobase.account3.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final RedisTestService redisTestService;

    @GetMapping("/get-lock")
    public String getLock() {
        return redisTestService.getLock();
    }

    @GetMapping("/create-account")
    public String createAccount() {
        accountService.createAccount();
        return "ok";
    }

    @GetMapping("/account/{id}")
    public Account getAccount(
            @PathVariable Long id
    ) {
        return accountService.getAccount(id);
    }


}
