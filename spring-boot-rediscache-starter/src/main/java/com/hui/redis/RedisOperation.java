package com.hui.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 0.0.1.
 * @Description RedisOperation
 * @Author Hui
 * @Date 2017/6/5 0005
 */
interface RedisOperation {

    boolean exists(String key);

    boolean exists(byte[] key);

    String setBatch(final int DBIndex, final Map<String, String> values);

    Map<String, String> getBatch(final int DBIndex, final List<String> keys);

    String hsetBatch(final int DBIndex, final String key, final Map<String, String> values);

    String set(final String key, final String value);

    String set(final String key, final String value, final int dbIndex);

    String set(final String key, final String value, final int dbIndex, final int seconds);

    String set(final byte[] key, final byte[] value);

    String set(final byte[] key, final byte[] value, final int dbIndex);

    String set(final byte[] key, final byte[] value, final int dbIndex, final int seconds);

    String get(final String key);


    String type(final String key);

    Long expire(final String key, final int seconds);

    Long expireAt(final String key, final long unixTime);

    Long ttl(final String key);

    Boolean setbit(final String key, final long offset, final boolean value);

    Boolean setbit(final String key, final long offset, final String value);

    Boolean getbit(final String key, final long offset);

    Long setrange(final String key, final long offset, final String value);

    String getrange(final String key, final long startOffset, final long endOffset);

    String getSet(final String key, final String value);

    Long setnx(final String key, final String value);

    String setex(final String key, final int seconds, final String value);

    Long decrBy(final String key, final long integer);

    Long decr(final String key);

    Long incrBy(final String key, final long integer);

    Long incr(final String key);

    Long append(final String key, final String value);

    String substr(final String key, final int start, final int end);

    Long hset(final String key, final String field, final String value);

    String hget(final String key, final String field);

    Long hsetnx(final String key, final String field, final String value);

    String hmset(final String key, final Map<String, String> hash);

    List<String> hmget(final String key, final String... fields);

    Long hincrBy(final String key, final String field, final long value);

    Boolean hexists(final String key, final String field);

    Long del(final String key);

    Long hdel(final String key, final String... fields);

    Long hlen(final String key);

    Set<String> hkeys(final String key);

    List<String> hvals(final String key);

    Map<String, String> hgetAll(final String key);

    Long rpush(final String key, final String... strings);

    Long lpush(final String key, final String... strings);

    Long lpushx(final String key, final String string);

    Long strlen(final String key);

    Long move(final String key, final int dbIndex);

    Long rpushx(final String key, final String string);

    Long persist(final String key);

    Long llen(final String key);

    List<String> lrange(final String key, final long start, final long end);

    String ltrim(final String key, final long start, final long end);

    String lindex(final String key, final long index);

    String lset(final String key, final long index, final String value);

    Long lrem(final String key, final long count, final String value);

    String lpop(final String key);

    String rpop(final String key);

    Long sadd(final String key, final String... members);

    Set<String> smembers(final String key);

    Long srem(final String key, final String... members);

    String spop(final String key);

    Long scard(final String key);

}
