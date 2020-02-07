package com.planner.trip.controller;

import com.planner.trip.code.ResultCode;
import com.planner.trip.payload.JwtAuthenticationResponse;
import com.planner.trip.payload.Result;
import com.planner.trip.payload.SignInRequest;
import com.planner.trip.payload.SignUpRequest;
import com.planner.trip.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Log
public class AuthController {

    private AuthService authService;

    @PostMapping("/signup")
    public Result registerUser(@RequestBody SignUpRequest dto) {
        authService.signUp(dto);

        Result result = Result.builder().
                code(ResultCode.SUCCESS).
                data(dto).
                build();

        return result;
    }

    @PostMapping("/signin")
    public Result authenticateUser(@RequestBody SignInRequest dto) {
        Optional<JwtAuthenticationResponse> jwt = Optional.ofNullable(authService.signIn(dto));


        Result result = Result.builder()
                .code(ResultCode.SUCCESS)
                .data(jwt)
                .build();

        return result;
    }

}
