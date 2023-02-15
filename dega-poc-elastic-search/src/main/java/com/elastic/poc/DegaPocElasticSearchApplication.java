package com.elastic.poc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info= @Info(title = "Test", version = "1.0", description = "Test"))
public class DegaPocElasticSearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(DegaPocElasticSearchApplication.class, args);
	}
}
