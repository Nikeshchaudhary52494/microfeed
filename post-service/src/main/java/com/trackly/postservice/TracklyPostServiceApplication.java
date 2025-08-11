package com.trackly.postservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class TracklyPostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracklyPostServiceApplication.class, args);
	}

}
