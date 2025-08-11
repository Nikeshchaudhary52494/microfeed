package com.trackly.userprofileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserProfileResponse {
    private Long id;
    private String username;
    private String bio;
    private String avatarUrl;
}
