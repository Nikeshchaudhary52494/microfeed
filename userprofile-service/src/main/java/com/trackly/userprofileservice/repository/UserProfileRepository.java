package com.trackly.userprofileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackly.userprofileservice.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
