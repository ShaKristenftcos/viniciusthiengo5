package com.hui.autoConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @version 0.0.1.
 * @Description RedisConfig
 * @Author Hui
 * @Date 2017/6/5 0005
 */

@Configuration
public class RedisCacheAutoConfiguration {

    private RedisProperties redisProperties = null;

    @Autowired
    public RedisCacheAutoConfiguration() {
        redisProperties = new RedisProperties();
        redisProperties.setHost("127.0.0.1");
        this.redisProperties = redisProperties;
    }


    @Bean
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool(redisProperties.getHost(), redisProperties.getPort());
        return jedisPool;
    }
}
