package com.hui.cache;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @version 0.0.1.
 * @Description RedisCache
 * @Author Hui
 * @Date 2017/6/5 0005
 */
public interface RedisCache {

    /**
     * @param args
     * @param method
     * @param joinPoint
     * @return
     * @throws Throwable
     * @description redisCacheGet注解解析方法
     */
    Object redisCacheGet(Object[] args, Method method, ProceedingJoinPoint joinPoint) throws Throwable;


    /**
     * @param args
     * @param method
     * @param joinPoint
     * @return
     * @throws Throwable
     * @description redisCache 清除方法
     */
    Object redisCacheClean(Object[] args, Method method, ProceedingJoinPoint joinPoint) throws Throwable;
}

