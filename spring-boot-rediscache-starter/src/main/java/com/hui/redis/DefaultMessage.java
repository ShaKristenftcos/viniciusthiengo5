package com.hui.redis;

/**
 * @version 0.0.1.
 * @Description DefaultMessage
 * @Author Hui
 * @Date 2017/6/7 0007
 */
public class DefaultMessage implements Message {

    private final byte[] channel;
    private final byte[] body;
    private String toString;

    public DefaultMessage(byte[] channel, byte[] body) {
        this.channel = channel;
        this.body = body;
    }

    @Override
    public byte[] getBody() {
        return body != null ? body.clone() : null;
    }

    @Override
    public byte[] getChannel() {
        return channel != null ? channel.clone() : null;
    }

    public String toString() {
        if (toString == null) {
            toString = new String(body.toString());
        }
        return toString;
    }
}
