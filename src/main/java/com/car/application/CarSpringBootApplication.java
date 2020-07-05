package com.car.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.car")
@EnableJpaRepositories(basePackages = "com.car")
@EntityScan(basePackages = "com.car")
public class CarSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarSpringBootApplication.class, args);
	}
}