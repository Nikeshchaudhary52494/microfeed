package com.microfeed.postservice.events;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreatedEvent {
    private Long postId;
    private Long authorId;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
}
