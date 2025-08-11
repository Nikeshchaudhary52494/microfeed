package com.microfeed.userprofileservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserProfileRequest {
    @NotBlank(message = "Username is required")
    private String username;
    private String bio;
    private String avatarUrl;
}
