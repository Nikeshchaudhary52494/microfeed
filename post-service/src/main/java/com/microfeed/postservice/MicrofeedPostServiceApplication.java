package com.microfeed.postservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MicrofeedPostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrofeedPostServiceApplication.class, args);
	}

}
