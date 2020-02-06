package com.planner.trip.controller;

import com.planner.trip.code.ResultCode;
import com.planner.trip.payload.JwtAuthenticationResponse;
import com.planner.trip.payload.Result;
import com.planner.trip.payload.SignInRequest;
import com.planner.trip.payload.SignUpRequest;
import com.planner.trip.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Log
public class AuthController {

    private UserService userService;

    @PostMapping("/signup")
    public Result registerUser(@RequestBody SignUpRequest dto) {
        log.info("asdddddddddddddddddddddddddddddddddddd");
        userService.signUp(dto);

        Result result = Result.builder().
                code(ResultCode.SUCCESS).
                data(dto).
                build();

        return result;
    }

    @PostMapping("/signin")
    public Result authenticateUser(@RequestBody SignInRequest dto) {
        Optional<JwtAuthenticationResponse> jwt = Optional.ofNullable(userService.signIn(dto));

        ResultCode resultCode = ResultCode.UNAUTHORIZED;
        if(jwt.isPresent()) {
            resultCode = ResultCode.SUCCESS;
        }

        Result result = Result.builder()
                .code(resultCode)
                .data(jwt)
                .build();

        return result;
    }

}
