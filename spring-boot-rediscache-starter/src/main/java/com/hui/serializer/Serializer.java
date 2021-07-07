
package com.hui.serializer;

/**
 * @param <T> source
 * @param <R> target
 */
public interface Serializer<T, R> {

    /**
     * Serialize the given object to binary data.
     *
     * @param t object to serialize
     * @return the equivalent binary data
     */
    R serialize(T t) throws SerializationException;

    /**
     * Deserialize an object from the given binary data.
     *
     * @param r object binary representation
     * @return the equivalent object instance
     */
    T deserialize(R r) throws SerializationException;
}
