package com.microfeed.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microfeed.postservice.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
