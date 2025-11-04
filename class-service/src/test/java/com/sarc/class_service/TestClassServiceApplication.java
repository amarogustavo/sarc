package com.sarc.class_service;

import org.springframework.boot.SpringApplication;

public class TestClassServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ClassServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
