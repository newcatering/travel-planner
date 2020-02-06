package com.planner.trip.service;

import com.planner.trip.code.Auth;
import com.planner.trip.config.security.JwtManager;
import com.planner.trip.model.User;
import com.planner.trip.payload.JwtAuthenticationResponse;
import com.planner.trip.payload.SignInRequest;
import com.planner.trip.payload.SignUpRequest;
import com.planner.trip.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtManager jwtManager;

    @Transactional
    public void signUp(SignUpRequest dto) {
        verifyDuplicatedUser(dto.getEmail());

        userRepo.save(User.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhone())
                .auth(Auth.USER)
                .build());
    }

    private void verifyDuplicatedUser(String userEmail) {
        if (userRepo.existsByEmail(userEmail)) {
            throw new IllegalArgumentException("중복된 유저입니다.");
        }
    }

    public JwtAuthenticationResponse signIn(SignInRequest dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtManager.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }
}
