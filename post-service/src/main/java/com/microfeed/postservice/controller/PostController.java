package com.microfeed.postservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microfeed.postservice.dto.PostRequest;
import com.microfeed.postservice.dto.PostResponse;
import com.microfeed.postservice.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    @PostMapping()
    public PostResponse createPost(@Valid @RequestBody PostRequest request) {
        return service.createPost(request);
    }

    @GetMapping
    public Page<PostResponse> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.getAllPosts(page, size);
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id) {
        return service.getPost(id);
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable Long id, @Valid @RequestBody PostRequest request) {
        return service.updatePost(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        service.deletePost(id);
    }

    @PostMapping("/{id}/like")
    public PostResponse likePost(@PathVariable Long id) {
        return service.likePost(id);
    }

    @PostMapping("/{id}/comment")
    public PostResponse commentPost(@PathVariable Long id) {
        return service.commentPost(id);
    }
}
