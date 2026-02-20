package com.backend.Service.impl;

import com.backend.DTO.AuthResponse;
import com.backend.DTO.LoginRequest;
import com.backend.DTO.RegisterRequest;
import com.backend.Entity.Enum.Role;
import com.backend.Entity.User;
import com.backend.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (request.getRole() == null) {
            request.setRole(Role.CUSTOMER);
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepo.save(user);

        return AuthResponse.builder()
                .message("Registration Successfully Done.").build();

    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        return AuthResponse.builder()
                .message("Login Success.").build();
    }

}
