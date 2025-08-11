package com.trackly.postservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String content;
    private String imageUrl;
    private Long authorId;
    private int likesCount;
    private int commentsCount;
    private LocalDateTime createdAt;
}
