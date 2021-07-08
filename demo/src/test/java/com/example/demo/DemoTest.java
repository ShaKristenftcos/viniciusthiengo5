package com.example.demo;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 0.0.1.
 * @Description DemoTest
 * @Author Hui
 * @Date 2017/6/15 0015
 */
public class DemoTest {

    @Test
    public void demo(){
        System.out.println("DemoTest.demo =>" + (60>>1));
        System.out.println(Integer.MAX_VALUE);
        int h;
        String key = "yhq";
        System.out.println(h = key.hashCode());
        System.out.println(h >>> 16);
        System.out.println();
    }
}
