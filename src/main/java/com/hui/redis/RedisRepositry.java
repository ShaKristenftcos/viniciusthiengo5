package com.hui.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.util.SafeEncoder;

import java.util.*;

@Repository
public class RedisRepositry extends AbstractRedisRepositry implements RedisOperation{

    protected RedisRepositry(JedisPool jedisPool) {
        super(jedisPool);
    }

    public boolean exists(String key) {
        return doOperation(jedis -> jedis.exists(key));
    }


    public String setBatch(final int DBIndex, final Map<String, String> values) {
        return doOperation(jedis -> {
            jedis.select(DBIndex);
            Pipeline p = jedis.pipelined();
            Set<String> keys = values.keySet();
            for (String key : keys) {
                p.set(key, values.get(key));
            }
            p.sync();
            return values.toString();
        });
    }

    public Map<String, String> getBatch(final int DBIndex, final List<String> keys) {
        return doOperation(jedis -> {
            Map<String, String> result = new HashMap<>();
            Map<String, Response<String>> resultR = new HashMap<>();
            jedis.select(DBIndex);
            Pipeline p = jedis.pipelined();
            for (String key1 : keys) {
                resultR.put(key1, p.get(key1));
            }
            p.sync();
            Set<String> sets = resultR.keySet();
            for (String key : sets) {
                result.put(key, resultR.get(key).get());
            }
            return result;
        });
    }

    public String hsetBatch(final int DBIndex, final String key, final Map<String, String> values) {
        return doOperation(jedis -> {
            jedis.select(DBIndex);
            Pipeline p = jedis.pipelined();
            Set<String> keys = values.keySet();
            for (String fiedlKey : keys) {
                p.hset(key, fiedlKey, values.get(key));
            }
            p.sync();
            return values.toString();
        });
    }

    public String set(final String key, final String value) {
        return doOperation(jedis -> jedis.set(key, value));
    }

    public String get(final String key) {
        return doOperation(jedis -> jedis.get(key));
    }


    public String type(final String key) {
        return doOperation(jedis -> jedis.type(key));
    }

    public Long expire(final String key, final int seconds) {
        return doOperation(jedis -> jedis.expire(key, seconds));
    }

    public Long expireAt(final String key, final long unixTime) {
        return doOperation(jedis -> jedis.expireAt(key, unixTime));
    }

    public Long ttl(final String key) {
        return doOperation(jedis -> jedis.ttl(key));
    }

    public Boolean setbit(final String key, final long offset, final boolean value) {
        return doOperation(jedis -> jedis.setbit(key, offset, value));
    }

    public Boolean setbit(final String key, final long offset, final String value) {
        return doOperation(jedis -> jedis.setbit(SafeEncoder.encode(key), offset, SafeEncoder.encode(value)));
    }

    public Boolean getbit(final String key, final long offset) {
        return doOperation(jedis -> jedis.getbit(key, offset));
    }

    public Long setrange(final String key, final long offset, final String value) {
        return doOperation(jedis -> jedis.setrange(key, offset, value));
    }

    public String getrange(final String key, final long startOffset, final long endOffset) {
        return doOperation(jedis -> jedis.getrange(key, startOffset, endOffset));
    }

    public String getSet(final String key, final String value) {
        return doOperation(jedis -> jedis.getSet(key, value));
    }

    public Long setnx(final String key, final String value) {
        return doOperation(jedis -> jedis.setnx(key, value));
    }

    public String setex(final String key, final int seconds, final String value) {
        return doOperation(jedis -> jedis.setex(key, seconds, value));
    }

    public Long decrBy(final String key, final long integer) {
        return doOperation(jedis -> jedis.decrBy(key, integer));
    }

    public Long decr(final String key) {
        return doOperation(jedis -> jedis.decr(key));
    }

    public Long incrBy(final String key, final long integer) {
        return doOperation(jedis -> jedis.incrBy(key, integer));
    }

    public Long incr(final String key) {
        return doOperation(jedis -> jedis.incr(key));
    }

    public Long append(final String key, final String value) {
        return doOperation(jedis -> jedis.append(key, value));
    }

    public String substr(final String key, final int start, final int end) {
        return doOperation(jedis -> jedis.substr(key, start, end));
    }

    public Long hset(final String key, final String field, final String value) {
        return doOperation(jedis -> jedis.hset(key, field, value));
    }

    public String hget(final String key, final String field) {
        return doOperation(jedis -> jedis.hget(key, field));
    }

    public Long hsetnx(final String key, final String field, final String value) {
        return doOperation(jedis -> jedis.hsetnx(key, field, value));
    }

    public String hmset(final String key, final Map<String, String> hash) {
        return doOperation(jedis -> jedis.hmset(key, hash));
    }

    public List<String> hmget(final String key, final String... fields) {
        return doOperation(jedis -> jedis.hmget(key, fields));
    }

    public Long hincrBy(final String key, final String field, final long value) {
        return doOperation(jedis -> jedis.hincrBy(key, field, value));
    }

    public Boolean hexists(final String key, final String field) {
        return doOperation(jedis -> jedis.hexists(key, field));
    }

    public Long del(final String key) {
        return doOperation(jedis -> jedis.del(key));
    }

    public Long hdel(final String key, final String... fields) {
        return doOperation(jedis -> jedis.hdel(key, fields));
    }

    public Long hlen(final String key) {
        return doOperation(jedis -> jedis.hlen(key));
    }

    public Set<String> hkeys(final String key) {
        return doOperation(jedis -> jedis.hkeys(key));
    }

    public List<String> hvals(final String key) {
        return doOperation(jedis -> jedis.hvals(key));
    }

    public Map<String, String> hgetAll(final String key) {
        return doOperation(jedis -> jedis.hgetAll(key));
    }

    public Long rpush(final String key, final String... strings) {
        return doOperation(jedis -> jedis.rpush(key, strings));
    }

    public Long lpush(final String key, final String... strings) {
        return doOperation(jedis -> jedis.lpush(key, strings));
    }

    public Long lpushx(final String key, final String string) {
        return doOperation(jedis -> jedis.lpushx(key, string));
    }

    public Long strlen(final String key) {
        return doOperation(jedis -> jedis.strlen(key));
    }

    public Long move(final String key, final int dbIndex) {
        return doOperation(jedis -> jedis.move(key, dbIndex));
    }

    public Long rpushx(final String key, final String string) {
        return doOperation(jedis -> jedis.rpushx(key, string));
    }

    public Long persist(final String key) {
        return doOperation(jedis -> jedis.persist(key));
    }

    public Long llen(final String key) {
        return doOperation(jedis -> jedis.llen(key));
    }

    public List<String> lrange(final String key, final long start,
                               final long end) {
        return doOperation(jedis -> jedis.lrange(key, start, end));
    }

    public String ltrim(final String key, final long start, final long end) {
        return doOperation(jedis -> jedis.ltrim(key, start, end));
    }

    public String lindex(final String key, final long index) {
        return doOperation(jedis -> jedis.lindex(key, index));
    }

    public String lset(final String key, final long index, final String value) {
        return doOperation(jedis -> jedis.lset(key, index, value));
    }

    public Long lrem(final String key, final long count, final String value) {
        return doOperation(jedis -> jedis.lrem(key, count, value));
    }

    public String lpop(final String key) {
        return doOperation(jedis -> jedis.lpop(key));
    }

    public String rpop(final String key) {
        return doOperation(jedis -> jedis.rpop(key));
    }

    public Long sadd(final String key, final String... members) {
        return doOperation(jedis -> jedis.sadd(key, members));
    }

    public Set<String> smembers(final String key) {
        return doOperation(jedis -> jedis.smembers(key));
    }

    public Long srem(final String key, final String... members) {
        return doOperation(jedis -> jedis.srem(key, members));
    }

    public String spop(final String key) {
        return doOperation(jedis -> jedis.spop(key));
    }

    public Long scard(final String key) {
        return doOperation(jedis -> jedis.scard(key));
    }

}