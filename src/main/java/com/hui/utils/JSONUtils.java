package com.hui.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author huiqiang.yang
 * @version JsonUtils
 * @time 2016/7/24 0024
 */
public class JSONUtils {

    private final static Logger log = LoggerFactory.getLogger(JSONUtils.class);

    private static ObjectMapper mapper;

    public static synchronized ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            return mapper;
        } else {
            return mapper;
        }
    }


    public static String toJsonStr(Object obj) {
        String jsonStr = "";
        try {
            if(obj!=null) {
                jsonStr = getMapper().writeValueAsString(obj);
            }
            log.info(jsonStr);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return jsonStr;
    }

    public static Object parse(String jsonStr, Class<?> bean) {
        Object obj = null;
        try {
            log.info(jsonStr);
            obj = getMapper().readValue(jsonStr, bean);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return obj;
    }

    public static List parseList(String jsonStr, Class<?> bean) {
        List obj = null;
        JavaType javaType = getCollectionType(List.class, bean);
        try {
            if(!StringUtils.isEmpty(jsonStr)) {
                obj = getMapper().readValue(jsonStr, javaType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static List parseCollection(String jsonStr, Class<?> collect, Class<?> bean) {
        List obj = null;
        JavaType javaType = getCollectionType(collect, bean);
        try {
            if(!StringUtils.isEmpty(jsonStr)) {
                obj = getMapper().readValue(jsonStr, javaType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Set parseSet(String jsonStr, Class<?> bean) {
        Set obj = null;
        JavaType javaType = getCollectionType(Set.class, bean);
        try {
            if(!StringUtils.isEmpty(jsonStr)) {
                obj = getMapper().readValue(jsonStr, javaType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Map parseMap(String jsonStr, Class<?> bean) {
        Map obj = null;
        JavaType javaType = getCollectionType(Map.class, bean);
        try {
            if(!StringUtils.isEmpty(jsonStr)) {
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
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
