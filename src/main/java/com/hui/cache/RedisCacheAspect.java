package com.hui.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * @version 0.0.1.
 * @Description 注解缓存切面
 * @Author Hui
 * @Date 2017/6/5 0005
 */
@Aspect
@Repository
public class RedisCacheAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final RedisCacheHandle redisCacheHandle;

    @Autowired
    public RedisCacheAspect(RedisCacheHandle redisCacheHandle) {
        this.redisCacheHandle = redisCacheHandle;
    }

    @Around(value = "@annotation(RedisCacheGet)")
    public Object cacheGet(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        return redisCacheHandle.redisCacheGet(args, method, joinPoint);

    }

    @Around(value = "@annotation(RedisCacheClean)")
    public Object cacheClean(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        return redisCacheHandle.redisCacheClean(args, method, joinPoint);

    }


}

