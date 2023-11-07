package com.auth.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Configuration  
@Slf4j
public class CvCreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvCreationApplication.class, args);
		log.info("Application started successfully ...!");
	}

}
