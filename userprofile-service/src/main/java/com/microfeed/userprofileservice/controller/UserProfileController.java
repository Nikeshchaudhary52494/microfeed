package com.microfeed.userprofileservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microfeed.userprofileservice.dto.UserProfileRequest;
import com.microfeed.userprofileservice.dto.UserProfileResponse;
import com.microfeed.userprofileservice.service.UserProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService service;

    @PostMapping
    public UserProfileResponse createProfile(@Valid @RequestBody UserProfileRequest request) {
        return service.createProfile(request);
    }

    @GetMapping
    public Page<UserProfileResponse> getAllProfiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.getAllProfiles(page, size);
    }

    @GetMapping("/{id}")
    public UserProfileResponse getProfile(@PathVariable Long id) {
        return service.getProfile(id);
    }

    @PutMapping("/{id}")
    public UserProfileResponse updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody UserProfileRequest request) {
        return service.updateProfile(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        service.deleteProfile(id);
    }
}
