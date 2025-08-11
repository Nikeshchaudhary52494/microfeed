package com.microfeed.authservice.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLikedEvent {
    private Long postId;
    private Long likedByUserId;
}
