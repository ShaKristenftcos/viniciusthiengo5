package com.hui.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

import java.io.*;

/**
 *
 * @author huiqiang.yang
 * @version SerializationUtils
 * @time 2016/7/24 0024
 */
@Component
public class JdkSerializer implements Serializer<Object, byte[]> {

    private final static Logger log = LoggerFactory.getLogger(JdkSerializer.class);

    public byte[] serialize(Object o) {
        if (o == null) {
            return new byte[0];
        }
        byte[] rv = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(o);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("Non-serializable object", e);
        }
        return rv;
    }

    public Object deserialize(byte[] in) {
        Object rv = null;
        try {
            if (in != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(in);
                ObjectInputStream is = new ObjectInputStream(bis);
                rv = is.readObject();
                is.close();
                bis.close();
            }
        } catch (IOException e) {
            log.warn("Caught IOException decoding %d bytes of data", e);
        } catch (ClassNotFoundException e) {
            log.warn("Caught CNFE decoding %d bytes of data", e);
        }
        return rv;
    }

    public static byte[][] encodeMany(final String... strs) {
        byte[][] many = new byte[strs.length][];
        for (int i = 0; i < strs.length; i++) {
            many[i] = encode(strs[i]);
        }
        return many;
    }

    public static byte[] encode(final String str) {
        try {
            if (str == null) {
                throw new JedisDataException("value sent to redis cannot be null");
            }
            return str.getBytes(Protocol.CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new JedisException(e);
        }
    }

    public static String decode(final byte[] data) {
        try {
            return new String(data, Protocol.CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new JedisException(e);
        }
    }
}
