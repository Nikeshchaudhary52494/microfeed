package com.microfeed.authservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MicrofeedAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrofeedAuthServiceApplication.class, args);
	}

}