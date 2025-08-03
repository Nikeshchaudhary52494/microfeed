package com.trackly.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackly.authservice.dto.UserRequestDTO;
import com.trackly.authservice.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth/v1")
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
