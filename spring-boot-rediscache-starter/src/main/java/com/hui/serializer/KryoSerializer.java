package com.hui.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author Hui
 * @version 0.0.1.
 * @apiNote KryoSerializer
 * @since 2017/7/6 0006
 */
public class KryoSerializer implements Serializer<Object, byte[]> {

    private Kryo kryo = new Kryo();

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        byte[] buffer = new byte[2048];
        Output output = new Output(buffer);
        kryo.writeClassAndObject(output, o);
        return output.toBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        Input input = new Input(bytes);
        Object t = kryo.readClassAndObject(input);
        return t;
    }
}
