package com.example.demo;

import com.hui.annotation.RedisCacheGet;
import org.springframework.stereotype.Service;

/**
 * @version 0.0.1.
 * @Description DemoService
 * @Author Hui
 * @Date 2017/6/6 0006
 */
@Service
public class DemoService {

    @RedisCacheGet(key = "'getHello'")
    public String getHello(){
        return "hello world";
    }
}
