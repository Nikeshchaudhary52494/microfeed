package com.trackly.userprofileservice.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.trackly.userprofileservice.entity.UserProfile;
import com.trackly.userprofileservice.events.PostCreatedEvent;
import com.trackly.userprofileservice.events.UserRegisteredEvent;
import com.trackly.userprofileservice.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProfileEventListener {

    private final UserProfileRepository profileRepository;

    // Create a default profile when user registers
    @RabbitListener(queues = RabbitConfig.USER_REGISTERED_QUEUE)
    public void handleUserRegistered(UserRegisteredEvent event) {
        // If profile already exists, skip
        if (profileRepository.existsById(event.getUserId())) {
            return;
        }

        UserProfile profile = UserProfile.builder()
                .userId(event.getUserId())
                .username(event.getUsername())
                .bio("")
                .avatarUrl(null)
                .postCount(0)
                .build();

        profileRepository.save(profile);
    }

    // Update profile stats on post created
    @RabbitListener(queues = RabbitConfig.POST_CREATED_QUEUE)
    public void handlePostCreated(PostCreatedEvent event) {
        profileRepository.findById(event.getAuthorId()).ifPresent(profile -> {
            profile.setPostCount(profile.getPostCount() + 1);
            profileRepository.save(profile);
        });
    }
}
