package com.hui.redis;

import redis.clients.jedis.Jedis;


public interface JedisCallBack<T> {

    T handle(Jedis jedis);

}