package com.zerobase.account3.controller;

import com.zerobase.account3.domain.Account;
import com.zerobase.account3.service.AccountService;
import com.zerobase.account3.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.zerobase.account3.dto.CreateAccount.Request;
import static com.zerobase.account3.dto.CreateAccount.Response;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final RedisTestService redisTestService;

    @PostMapping("/account")
    public Response createAccount(
            @RequestBody @Valid Request request
    ) {
        return Response.fromDto(
                accountService.createAccount(
                request.getUserId(),
                request.getInitialBalance()
            )
        );
    }

    @GetMapping("/get-lock")
    public String getLock() {
        return redisTestService.getLock();
    }

    @GetMapping("/account/{id}")
    public Account getAccount(
            @PathVariable Long id
    ) {
        return accountService.getAccount(id);
    }


}
