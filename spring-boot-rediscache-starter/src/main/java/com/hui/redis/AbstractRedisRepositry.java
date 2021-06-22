package com.hui.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

/**
 * @version 0.0.1.
 * @Description AbstractRedisRepositry
 * @Author Hui
 * @Date 2017/6/5 0005
 */
@Repository
public class AbstractRedisRepositry {

    @Autowired
    private JedisPool jedisPool;

    public <E> E doOperation(JedisCallBack<E> callback) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return callback.handle(jedis);
        } catch (JedisConnectionException e) {
            throw new JedisException("Faild when execute operation  " + e);
        } finally {
            if (null != jedisPool && null != jedis) {
                jedis.close();
            }
        }
    }
}
