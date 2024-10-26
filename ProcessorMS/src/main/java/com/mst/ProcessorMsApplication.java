package com.mst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition
public class ProcessorMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorMsApplication.class, args);
	}

}
