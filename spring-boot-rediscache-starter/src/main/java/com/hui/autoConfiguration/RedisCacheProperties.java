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

    public static class Redis{
        private String host;
        private int port = 6379;
        private Integer timeout = 5000;
        private String password = "";
        private int dbIndex;

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public Integer getTimeout() {
            return timeout;
        }

        public void setTimeout(Integer timeout) {
            this.timeout = timeout;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getDbIndex() {
            return dbIndex;
        }

        public void setDbIndex(int dbIndex) {
            this.dbIndex = dbIndex;
        }
    }
}
