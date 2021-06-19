package com.hui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootRediscacheStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRediscacheStarterApplication.class, args);
	}
}
