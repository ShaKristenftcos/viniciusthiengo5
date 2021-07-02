package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class DemoApplicationTests {

    @Autowired
    private DemoService demoService;


    @Test
    public void getHello() {
        long l = System.currentTimeMillis();
        demoService.getHello();
        System.out.println(System.currentTimeMillis() - l);
        l = System.currentTimeMillis();
        demoService.getHello();
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void updateHello() {
        demoService.updateHello();
    }

}
