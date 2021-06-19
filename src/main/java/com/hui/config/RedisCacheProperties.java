package com.hui.config;

/**
 * @version 0.0.1.
 * @Description RedisCacheProperties
 * @Author Hui
 * @Date 2017/6/6 0006
 */
public class RedisCacheProperties {
    private Redis redis;

    public Redis getRedis() {
        return redis;
    }

    public void setRedis(Redis redis) {
        this.redis = redis;
    }
}


class Redis {
    private String host;
    private int port;
    private String instanceid;
    private String password;

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

    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}