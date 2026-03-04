package com.fastag.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.fastag", "com.fastag.common"})
@SpringBootApplication
public class CommonLibApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonLibApplication.class, args);
	}

}