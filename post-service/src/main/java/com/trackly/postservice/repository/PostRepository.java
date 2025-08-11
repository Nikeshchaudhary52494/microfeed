package com.trackly.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackly.postservice.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
