package com.example.demo;

import com.example.demo.service.DemoService;
import com.hui.redis.RedisRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Iterator;

@SpringBootApplication
@EnableWebMvc
@RestController
public class DemoApplication {

	private final RedisRepositry redisRepositry;

	private final DemoService demoService;

	@Autowired
	public DemoApplication(RedisRepositry redisRepositry, DemoService demoService) {
		this.redisRepositry = redisRepositry;
		this.demoService = demoService;
	}

	@RequestMapping("/get")
	public String get(){
		redisRepositry.get("key");
		return demoService.getHello();
	}

	@RequestMapping("/update")
	public void update(){
		demoService.updateHello();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
