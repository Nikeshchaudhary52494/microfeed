package com.microfeed.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microfeed.authservice.dto.UserRequestDTO;
import com.microfeed.authservice.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserRequestDTO userRequestDTO) {
        boolean isCreated = userDetailsService.signUpUser(userRequestDTO);

        if (!isCreated) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email is already taken.");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully.");
    }
}
