package com.tech.redis.lock;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lw
 * @since 2023/6/20
 */
@Configuration
public class RedissonConfig {
    
    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.50.134:6379").setDatabase(0);
        return (Redisson)Redisson.create(config);
    }
}
