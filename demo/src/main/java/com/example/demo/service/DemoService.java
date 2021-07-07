package com.example.demo.service;

import com.hui.annotation.RedisCacheClean;
import com.hui.annotation.RedisCacheGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @version 0.0.1.
 * @Description DemoService
 * @Author Hui
 * @Date 2017/6/6 0006
 */
@Service
public class DemoService implements Serializable{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RedisCacheGet(key = "'getHello'")
    public String getHello(){
        return "hello world";
    }

    @RedisCacheClean(key = "'getHello'")
    public void updateHello(){
        log.info("com.example.demo.service.DemoService.updateHello");
    }

}
