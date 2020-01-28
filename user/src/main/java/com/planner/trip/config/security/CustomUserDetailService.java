package com.planner.trip.config.security;

import com.planner.trip.model.User;
import com.planner.trip.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// Spring Security User 검증
public class CustomUserDetailService implements UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    public CustomUserDetailService(UserRepo userRepo){
       this.userRepo = userRepo;
    }
    @Override// used by Spring Security
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found - email : "+ email)
        );
        return CustomUserDetails.create(user);
    }
    //used by JWTAuthenticationFilter
    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found - id: " + id)
        );
        return CustomUserDetails.create(user);
    }
}
