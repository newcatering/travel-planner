package com.planner.trip.controller;

import com.planner.trip.code.Auth;
import com.planner.trip.code.ResultCode;
import com.planner.trip.config.security.JwtManager;
import com.planner.trip.payload.JwtAuthenticationResponse;
import com.planner.trip.payload.Result;
import com.planner.trip.model.User;
import com.planner.trip.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class TestController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtManager jwtManager;

    @GetMapping("/test")
    public String test(){
        log.info("testdasdsad");
        User user = User.builder()
                        .email("test2@tt.com")
                        .nickname("테스터2")
                        .phone("01011111111")
                        .password(passwordEncoder.encode("test"))
                        .auth(Auth.USER).build();
        userRepo.save(user);
        return "aa";
    }
    @PostMapping("/api/login")
    public ResponseEntity<?> test2(){
        System.out.println("테스트");
        log.info("testdasdsad");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        "test@tt.com",
                        "test"
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtManager.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

    }

    @PostMapping("/data/tt")
    public ResponseEntity<?> tt(){
        Optional<User> user = userRepo.findByNickname("테스터");
        log.info(user.get().getAuth().toString());
        log.info(user.get().getAuth().name());
        System.out.println(user.get().getAuth().toString());
        System.out.println(user.get().getAuth().name());
        return ResponseEntity.ok("tt");
    }

    @GetMapping("/data/swag")
    public Result tt(User users){
        log.debug(users.getEmail());
        return new Result(ResultCode.SUCCESS,users.getEmail());
    }
}
