package com.hui.autoConfiguration;

import com.hui.cache.CacheException;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;

/**
 * @version 0.0.1.
 * @Description RedisConfig
 * @Author Hui
 * @Date 2017/6/5 0005
 */

@Configuration
@EnableConfigurationProperties(RedisCacheProperties.class)
@ComponentScan(basePackages = "com.hui")
public class RedisCacheAutoConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisCacheProperties redisProperties;

    @Bean
    public JedisPool reidsPool() {
        try {
            RedisCacheProperties.Redis redis = redisProperties.getRedis();
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            JedisPool jedisPool = new JedisPool(poolConfig, redis.getHost(), redis.getPort(), redis.getTimeout(), redis.getPassword(), redis.getDbIndex());
            return jedisPool;
        } catch (Exception e) {
            throw new CacheException("jedisPool init faild!", e.getCause());
        }
    }

    @PostConstruct
    public void init() {
        log.info("RedisCacheAutoConfiguration run start");
    }
}
