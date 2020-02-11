package com.planner.trip.service;

import com.planner.trip.model.User;
import com.planner.trip.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepo userRepo;

    public User findById(Long idx) throws Exception {
        return userRepo.findById(idx)
                .orElseThrow( () -> new Exception("")); // 임시
    }

}
