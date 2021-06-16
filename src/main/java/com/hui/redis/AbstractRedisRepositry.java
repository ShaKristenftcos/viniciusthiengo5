package com.hui.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.SafeEncoder;

/**
 * @version 0.0.1.
 * @Description AbstractRedisRepositry
 * @Author Hui
 * @Date 2017/6/5 0005
 */
public abstract class AbstractRedisRepositry {

    private final JedisPool jedisPool;

    @Autowired
    protected AbstractRedisRepositry(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public <E> E doOperation(JedisCallBack<E> callback) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return callback.handle(jedis);
        } catch (JedisConnectionException e) {
            throw new JedisException("Faild when execute operation  " + e);
        } finally {
            if (null != jedisPool && null != jedis) {
                jedisPool.close();
            }
        }
    }
}
