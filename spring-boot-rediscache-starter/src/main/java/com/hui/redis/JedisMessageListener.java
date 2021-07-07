package com.hui.redis;

import org.springframework.util.Assert;
import redis.clients.jedis.BinaryJedisPubSub;

/**
 * @version 0.0.1.
 * @Description JedisMessageListener
 * @Author Hui
 * @Date 2017/6/7 0007
 */
public class JedisMessageListener extends BinaryJedisPubSub {


    private final MessageListener listener;

    JedisMessageListener(MessageListener listener) {
        Assert.notNull(listener, "message listener is required");
        this.listener = listener;
    }

    /**
     * 取得订阅的消息后的处理
     *
     * @param channel
     * @param message
     */
    public void onMessage(byte[] channel, byte[] message) {
        listener.onMessage(new DefaultMessage(channel, message));
    }

    /**
     * 初始化订阅时处理
     *
     * @param channel
     * @param subscribedChannels
     */
    public void onSubscribe(byte[] channel, int subscribedChannels) {
        // you maybe do something from here
    }

    /**
     * 取消订阅时处理
     *
     * @param channel
     * @param subscribedChannels
     */
    public void onUnsubscribe(byte[] channel, int subscribedChannels) {
        // you maybe do something from here
    }

    /**
     * 初始化按表达式的方式订阅时候的处理
     *
     * @param pattern
     * @param subscribedChannels
     */
    public void onPSubscribe(byte[] pattern, int subscribedChannels) {
        // you maybe do something from here
    }

    /**
     * 取消按表达式的方式订阅时候的处理
     *
     * @param pattern
     * @param subscribedChannels
     */
    public void onPUnsubscribe(byte[] pattern, int subscribedChannels) {
        // you maybe do something from here
    }

}
