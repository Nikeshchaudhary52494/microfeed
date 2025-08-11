package com.trackly.authservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.trackly.authservice.events.UserRegisteredEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishUserRegistered(UserRegisteredEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                RabbitConfig.USER_REGISTERED_ROUTING_KEY,
                event);
    }
}
