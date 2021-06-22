package com.hui.annotation;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RedisCacheClean {

    /**
     * key值
     *
     * @return
     */
    public String[] key();
}