package com.spring.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringFirstRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFirstRestApiApplication.class, args);
	}

}
