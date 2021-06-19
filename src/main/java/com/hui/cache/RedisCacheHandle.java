package com.hui.cache;

import com.hui.annotation.RedisCacheClean;
import com.hui.annotation.RedisCacheGet;
import com.hui.redis.RedisRepositry;
import com.hui.serializer.SerializationUtils;
import com.hui.utils.JSONUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @version 0.0.1.
 * @Description RedisCachehandle
 * @Author Hui
 * @Date 2017/6/5 0005
 */
@Repository
public class RedisCacheHandle implements RedisCache {

    private static final int ONEDAY = 24 * 60 * 60;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final RedisRepositry redisRepositry;

    @Autowired
    public RedisCacheHandle(RedisRepositry redisRepositry) {
        this.redisRepositry = redisRepositry;
    }

    @Override
    public Object redisCacheGet(Object[] args, Method method, ProceedingJoinPoint joinPoint) throws Throwable {

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();

        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        //如果有这个注解，则获取注解类
        RedisCacheGet methodType = method.getAnnotation(RedisCacheGet.class);
        String key = parser.parseExpression(methodType.key()).getValue(context, String.class);

        if (methodType.dataType() == RedisCacheGet.DataType.JSON) {//JSON形式保存
            if (redisRepositry.exists(key)) {
                long old = System.currentTimeMillis();
                String json = redisRepositry.get(key);
                log.debug("JSON read from redis spend > " + (System.currentTimeMillis() - old) + " <millis");
                if (method.getGenericReturnType().toString().contains("List") ||
                        method.getGenericReturnType().toString().contains("Set") ||
                        method.getGenericReturnType().toString().contains("Map")) {
                    String clazzName = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0].toString().substring(6);
                    Object o = Class.forName(clazzName).newInstance();
                    List list = JSONUtils.parseCollection(json, method.getReturnType(), o.getClass());
                    return list;
                } else {
                    return JSONUtils.parse(json, method.getReturnType());
                }
            } else {//查询数据，缓存，返回对象
                Object object = joinPoint.proceed(args);
                setRedisValueJson(methodType, key, object);
                return object;
            }
        } else {//CLASS形式保存
            if (redisRepositry.exists(key)) {//对象存在直接返回
                long old = System.currentTimeMillis();
                Object o = redisRepositry.get(key);
                log.debug("CLASS read from redis spend > " + (System.currentTimeMillis() - old) + " <millis");
                return o;
            } else {//查询数据，缓存，返回对象
                long l = System.currentTimeMillis();
                Object object = joinPoint.proceed(args);
                log.debug("read from DB spend > " + (System.currentTimeMillis() - l) + " <millis");
                setRedisValueClass(methodType, key, object);
                return object;
            }

        }
    }

    @Override
    public Object redisCacheClean(Object[] args, Method method, ProceedingJoinPoint joinPoint) throws Throwable {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();

        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        //如果有这个注解，则获取注解类
        Object object = joinPoint.proceed(args);

        //如果有这个注解，则获取注解类
        RedisCacheClean methodType = method.getAnnotation(RedisCacheClean.class);
        for (String str : methodType.key()) {
            String key = parser.parseExpression(str).getValue(context, String.class);
            if (str.contains("*")) {
                Set<String> keys = redisRepositry.hkeys(key);
                Iterator<String> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    redisRepositry.del(iterator.next());
                }
            }
            redisRepositry.del(key);
        }
        return object;
    }

    /**
     * @param methodType
     * @param key
     * @param object
     */
    private void setRedisValueClass(RedisCacheGet methodType, String key, Object object) {
        long timeMillis = System.currentTimeMillis();
        if (object != null) {
            //设置缓存时长
           /* if (methodType.expire() == 0) {
                redisRepositry.set(key, object);
            } else if (methodType.expire() == 1) {
                redisRepositry.set(SerializationUtils.encode(key), SerializationUtils.serialize(object), ONEDAY);
            } else {
                redisRepositry.set(SerializationUtils.encode(key), SerializationUtils.serialize(object), methodType.expire());
            }*/
        }
        long l = System.currentTimeMillis() - timeMillis;
        log.debug("CLASS write from redis spend > " + l + " <millis");

    }

    /**
     * @param methodType
     * @param key
     * @param object
     */
    private void setRedisValueJson(RedisCacheGet methodType, String key, Object object) {
        long timeMillis = System.currentTimeMillis();
        if (object != null) {
            if (methodType.expire() == 0) {//0:永不过期
                redisRepositry.set(key, JSONUtils.toJsonStr(object));
            }
        }
        long l = System.currentTimeMillis() - timeMillis;
        log.debug("JSON write from redis spend > " + l + " <millis");
    }

}


