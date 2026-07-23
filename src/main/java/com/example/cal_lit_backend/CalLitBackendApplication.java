package com.example.cal_lit_backend;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class CalLitBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(CalLitBackendApplication.class, args);

	}

}
