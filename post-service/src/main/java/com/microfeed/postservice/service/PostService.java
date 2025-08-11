package com.microfeed.postservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microfeed.postservice.dto.PostRequest;
import com.microfeed.postservice.dto.PostResponse;
import com.microfeed.postservice.entity.Post;
import com.microfeed.postservice.events.PostCreatedEvent;
import com.microfeed.postservice.messaging.PostEventPublisher;
import com.microfeed.postservice.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final PostEventPublisher postEventPublisher;

    public PostResponse createPost(PostRequest request) {
        Post post = Post.builder()
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .authorId(request.getAuthorId())
                .build();
        repository.save(post);
        System.out.println("this post is created");
        System.out.println(post.toString());
        PostCreatedEvent event = PostCreatedEvent.builder()
                .postId(post.getId())
                .authorId(post.getAuthorId())
                .imageUrl(post.getImageUrl())
                .content(post.getContent())
                .build();

        postEventPublisher.publishPostCreated(event);
        return mapToResponse(post);
    }

    public Page<PostResponse> getAllPosts(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .map(this::mapToResponse);
    }

    public PostResponse getPost(Long id) {
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public PostResponse updatePost(Long id, PostRequest request) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());
        repository.save(post);
        return mapToResponse(post);
    }

    public void deletePost(Long id) {
        repository.deleteById(id);
    }

    public PostResponse likePost(Long id) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setLikesCount(post.getLikesCount() + 1);
        repository.save(post);
        return mapToResponse(post);
    }

    public PostResponse commentPost(Long id) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setCommentsCount(post.getCommentsCount() + 1);
        repository.save(post);
        return mapToResponse(post);
    }

    private PostResponse mapToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .authorId(post.getAuthorId())
                .likesCount(post.getLikesCount())
                .commentsCount(post.getCommentsCount())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
