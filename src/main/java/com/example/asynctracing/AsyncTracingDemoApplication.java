package com.example.asynctracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncTracingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncTracingDemoApplication.class, args);
	}

}
