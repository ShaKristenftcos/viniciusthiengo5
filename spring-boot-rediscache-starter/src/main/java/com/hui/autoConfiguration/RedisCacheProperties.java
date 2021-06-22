package com.hui.autoConfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @version 0.0.1.
 * @Description RedisCacheProperties
 * @Author Hui
 * @Date 2017/6/6 0006
 */

@ConfigurationProperties("redisCache")
public class RedisCacheProperties {
    private Redis redis;

    public Redis getRedis() {
        return redis;
    }

    public void setRedis(Redis redis) {
        this.redis = redis;
    }

    public static class Redis {
        private String host = "127.0.0.1";
        private int port = 6379;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
