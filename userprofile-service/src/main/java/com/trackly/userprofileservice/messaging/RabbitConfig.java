package com.trackly.userprofileservice.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Exchange name
    public static final String TOPIC_EXCHANGE = "social.topic.exchange";

    // Routing keys / queues
    public static final String USER_REGISTERED_QUEUE = "user.registered.queue";
    public static final String USER_REGISTERED_ROUTING_KEY = "user.registered";

    public static final String POST_CREATED_QUEUE = "post.created.queue";
    public static final String POST_CREATED_ROUTING_KEY = "post.created";

    public static final String POST_LIKED_QUEUE = "post.liked.queue";
    public static final String POST_LIKED_ROUTING_KEY = "post.liked";

    public static final String POST_COMMENTED_QUEUE = "post.commented.queue";
    public static final String POST_COMMENTED_ROUTING_KEY = "post.commented";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public Queue userRegisteredQueue() {
        return QueueBuilder.durable(USER_REGISTERED_QUEUE).build();
    }

    @Bean
    public Queue postCreatedQueue() {
        return QueueBuilder.durable(POST_CREATED_QUEUE).build();
    }

    @Bean
    public Queue postLikedQueue() {
        return QueueBuilder.durable(POST_LIKED_QUEUE).build();
    }

    @Bean
    public Queue postCommentedQueue() {
        return QueueBuilder.durable(POST_COMMENTED_QUEUE).build();
    }

    @Bean
    public Binding bindUserRegistered(Queue userRegisteredQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userRegisteredQueue).to(exchange).with(USER_REGISTERED_ROUTING_KEY);
    }

    @Bean
    public Binding bindPostCreated(Queue postCreatedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(postCreatedQueue).to(exchange).with(POST_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding bindPostLiked(Queue postLikedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(postLikedQueue).to(exchange).with(POST_LIKED_ROUTING_KEY);
    }

    @Bean
    public Binding bindPostCommented(Queue postCommentedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(postCommentedQueue).to(exchange).with(POST_COMMENTED_ROUTING_KEY);
    }

    // JSON converter so payloads are JSON
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
