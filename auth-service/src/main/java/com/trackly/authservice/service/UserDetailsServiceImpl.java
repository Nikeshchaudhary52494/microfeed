package com.trackly.authservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trackly.authservice.dto.UserRequestDTO;
import com.trackly.authservice.entity.User;
import com.trackly.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
        User newUser = new User(userRequestDTO.getName(), userRequestDTO.getEmail(), encodedPassword);
        userRepository.save(newUser);
        return true;
    }
}
