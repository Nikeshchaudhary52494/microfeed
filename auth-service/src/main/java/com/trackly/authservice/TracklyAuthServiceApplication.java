package com.trackly.authservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class TracklyAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracklyAuthServiceApplication.class, args);
	}

}