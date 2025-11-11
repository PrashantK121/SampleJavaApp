package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		System.out.println("Hello World7");
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}
