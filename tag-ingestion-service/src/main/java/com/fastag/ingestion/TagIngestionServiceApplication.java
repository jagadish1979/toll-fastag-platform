package com.fastag.ingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fastag", "com.fastag.common","com.fastag.ingestion"})
public class TagIngestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagIngestionServiceApplication.class, args);
	}

}
