package com.trackly.userprofileservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.trackly.userprofileservice.dto.UserProfileRequest;
import com.trackly.userprofileservice.dto.UserProfileResponse;
import com.trackly.userprofileservice.entity.UserProfile;
import com.trackly.userprofileservice.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileResponse createProfile(UserProfileRequest request) {
        UserProfile profile = UserProfile.builder()
                .username(request.getUsername())
                .bio(request.getBio())
                .avatarUrl(request.getAvatarUrl())
                .build();
        repository.save(profile);
        return mapToResponse(profile);
    }

    public Page<UserProfileResponse> getAllProfiles(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .map(this::mapToResponse);
    }

    public UserProfileResponse getProfile(Long id) {
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public UserProfileResponse updateProfile(Long id, UserProfileRequest request) {
        UserProfile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        profile.setUsername(request.getUsername());
        profile.setBio(request.getBio());
        profile.setAvatarUrl(request.getAvatarUrl());
        repository.save(profile);
        return mapToResponse(profile);
    }

    public void deleteProfile(Long id) {
        repository.deleteById(id);
    }

    private UserProfileResponse mapToResponse(UserProfile profile) {
        return UserProfileResponse.builder()
                .id(profile.getId())
                .username(profile.getUsername())
                .bio(profile.getBio())
                .avatarUrl(profile.getAvatarUrl())
                .build();
    }
}
