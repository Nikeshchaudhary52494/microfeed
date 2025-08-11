package com.microfeed.authservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microfeed.authservice.dto.UserRequestDTO;
import com.microfeed.authservice.entity.User;
import com.microfeed.authservice.events.UserRegisteredEvent;
import com.microfeed.authservice.messaging.AuthEventPublisher;
import com.microfeed.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthEventPublisher authEventPublisher;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public boolean checkIfUserAlreadyExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean signUpUser(UserRequestDTO userRequestDTO) {
        if (checkIfUserAlreadyExists(userRequestDTO.getEmail())) {
            return false;
        }

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        User newUser = User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(encodedPassword)
                .username(userRequestDTO.getUsername())
                .build();
        userRepository.save(newUser);
        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .userId(newUser.getId())
                .email(newUser.getEmail())
                .username(newUser.getUsername())
                .build();
        authEventPublisher.publishUserRegistered(event);
        return true;
    }
}
