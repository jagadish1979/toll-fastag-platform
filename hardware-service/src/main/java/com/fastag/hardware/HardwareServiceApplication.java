package com.fastag.hardware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.fastag","com.fastag.common"})
@SpringBootApplication
public class HardwareServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardwareServiceApplication.class, args);
	}

}
