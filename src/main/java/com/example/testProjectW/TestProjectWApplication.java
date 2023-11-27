package com.example.testProjectW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"com.example.testProjectW","com.example.testProjectW2"})
public class TestProjectWApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestProjectWApplication.class, args);
	}

}
