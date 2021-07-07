package com.hui.redis;

import java.io.Serializable;

/**
 * @version 0.0.1.
 * @Description Message
 * @Author Hui
 * @Date 2017/6/7 0007
 */
public interface Message extends Serializable {

    byte[] getBody();

    byte[] getChannel();
}
