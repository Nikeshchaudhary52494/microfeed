package com.trackly.authservice.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentedEvent {
    private Long postId;
    private Long commentedByUserId;
    private String commentText;
}
