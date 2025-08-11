package com.microfeed.userprofileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microfeed.userprofileservice.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
