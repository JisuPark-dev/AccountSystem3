package com.zerobase.account3.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class LocalRedisConfig {
    //설정파일에서 값을 가져옴
    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct // 해당 빈의 의존성 주입이 끝난 후에 한번 메서드 호출
    public void startRedis() {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy // 해당 빈 소멸 직전에 메서드 호출
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
