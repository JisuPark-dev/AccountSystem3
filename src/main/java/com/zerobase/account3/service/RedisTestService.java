package com.zerobase.account3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisTestService {
    private final RedissonClient redissonClient;

    public String getLock() {
        RLock lock = redissonClient.getLock("sampleLock");

        try {
            //leaseTime 후에 lock이 자동으로 해제됨
            boolean isLock = lock.tryLock(1, 3, TimeUnit.SECONDS);
            if (!isLock) {
                log.error("-============lock acuqisition failed==============");
                return "Lock failed";
            }
        } catch (Exception e) {
            log.error("Redis lock failed");
        }
        return "Lock success";
    }
}
