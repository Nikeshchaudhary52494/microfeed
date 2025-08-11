package com.trackly.postservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.trackly.postservice.events.PostCommentedEvent;
import com.trackly.postservice.events.PostCreatedEvent;
import com.trackly.postservice.events.PostLikedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishPostCreated(PostCreatedEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                RabbitConfig.POST_CREATED_ROUTING_KEY, event);
    }

    public void publishPostLiked(PostLikedEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                RabbitConfig.POST_LIKED_ROUTING_KEY, event);
    }

    public void publishPostCommented(PostCommentedEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                RabbitConfig.POST_COMMENTED_ROUTING_KEY, event);
    }
}
