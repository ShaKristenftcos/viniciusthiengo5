package com.hui.serializer;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author huiqiang.yang
 * @version JsonUtils
 * @time 2016/7/24 0024
 */
@Component
public class JsonSerializer<T> implements Serializer<T, String> {

    private final Logger log = LoggerFactory.getLogger(JsonSerializer.class);

    private ObjectMapper mapper;

    public synchronized ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            return mapper;
        } else {
            return mapper;
        }
    }

    @Override
    public String serialize(T source) throws SerializationException {
        String jsonStr = "";
        try {
            if (source != null) {
                jsonStr = getMapper().writeValueAsString(source);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return jsonStr;
    }

    @Override
    public T deserialize(String jsonStr) throws SerializationException {
        T obj = null;
/*        if (js)
        obj = getMapper().readValue(jsonStr, );*/
        return obj;
    }


    public List parseCollection(String jsonStr, Class<?> collect, Class<?> bean) {
        List obj = null;
        JavaType javaType = getCollectionType(collect, bean);
        try {
            if (!StringUtils.isEmpty(jsonStr)) {
                obj = getMapper().readValue(jsonStr, javaType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    private JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
