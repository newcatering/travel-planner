package com.planner.trip.repository;

import com.planner.trip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String username);
    Optional<User> findByNicknameOrEmail(String username, String email);
    Boolean existsByNickname(String username);
    Boolean existsByEmail(String email);
}
