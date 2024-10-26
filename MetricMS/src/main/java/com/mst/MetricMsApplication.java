package com.mst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class MetricMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricMsApplication.class, args);
	}

}
