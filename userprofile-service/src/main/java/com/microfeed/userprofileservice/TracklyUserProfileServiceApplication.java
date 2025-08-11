package com.microfeed.userprofileservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class TracklyUserProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracklyUserProfileServiceApplication.class, args);
	}

}
