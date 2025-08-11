package com.microfeed.postservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.microfeed.postservice.events.PostCommentedEvent;
import com.microfeed.postservice.events.PostCreatedEvent;
import com.microfeed.postservice.events.PostLikedEvent;

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
