package com.microfeed.userprofileservice.events;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreatedEvent {
    private Long postId;
    private Long authorId;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
}
