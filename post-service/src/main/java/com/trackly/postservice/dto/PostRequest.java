package com.trackly.postservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostRequest {
    @NotBlank(message = "Content is required")
    private String content;
    private String imageUrl;
    private Long authorId;
}
