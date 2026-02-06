package com.eck.Smart.Agenda.service;

import com.eck.Smart.Agenda.dto.auth.AuthResponse;
import com.eck.Smart.Agenda.dto.auth.LoginRequest;
import com.eck.Smart.Agenda.dto.auth.RegisterRequest;
import com.eck.Smart.Agenda.dto.auth.UserResponse;
import com.eck.Smart.Agenda.entity.User;
import com.eck.Smart.Agenda.repository.UserRepository;
import com.eck.Smart.Agenda.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;

        @Transactional
        public AuthResponse register(RegisterRequest request) {
                if (userRepository.existsByEmail(request.getEmail())) {
                        throw new IllegalArgumentException("Email already exists");
                }

                String hashedPassword = passwordEncoder.encode(request.getPassword());

                User user = User.builder()
                                .email(request.getEmail())
                                .passwordHash(hashedPassword)
                                .fullName(request.getFullName())
                                .role("USER")
                                .build();

                User savedUser = userRepository.save(user);

                String token = jwtService.generateToken(
                                savedUser.getId(),
                                savedUser.getEmail(),
                                savedUser.getRole());

                return AuthResponse.builder()
                                .token(token)
                                .userId(savedUser.getId())
                                .email(savedUser.getEmail())
                                .fullName(savedUser.getFullName())
                                .build();
        }

        public AuthResponse login(LoginRequest request) {
                User user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

                if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
                        throw new IllegalArgumentException("Invalid email or password");
                }

                String token = jwtService.generateToken(
                                user.getId(),
                                user.getEmail(),
                                user.getRole());

                return AuthResponse.builder()
                                .token(token)
                                .userId(user.getId())
                                .email(user.getEmail())
                                .fullName(user.getFullName())
                                .build();
        }

        public UserResponse getUserResponse(User user) {
                return UserResponse.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .fullName(user.getFullName())
                                .role(user.getRole())
                                .createdAt(user.getCreatedAt())
                                .build();
        }
}
