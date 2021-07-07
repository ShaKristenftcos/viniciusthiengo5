package com.hui.redis;

/**
 * @version 0.0.1.
 * @Description MessageListener
 * @Author Hui
 * @Date 2017/6/7 0007
 */
public interface MessageListener {

    /**
     * redis消息监听
     *
     * @param message
     */
    void onMessage(Message message);

}
