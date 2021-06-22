package com.hui.autoConfiguration;

import com.hui.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
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
            JedisPool jedisPool = new JedisPool(redis.getHost(), redis.getPort());
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
