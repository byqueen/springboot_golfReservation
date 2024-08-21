package com.tinyego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tinyego", "com.tiny"})
public class ThePremiumGolfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThePremiumGolfApplication.class, args);
	}

}
