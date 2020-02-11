package com.planner.trip.repository;

import com.planner.trip.model.Plan;
import com.planner.trip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String username);
    Optional<User> findByNicknameOrEmail(String username, String email);
    Boolean existsByNickname(String username);
    Boolean existsByEmail(String email);

/*    @Query("SELECT u " +
            "FROM User u " +
            "WHERE u.nickname = :nickname " +
            "ORDER BY u.id ASC")
    List<Plan> findAllAsc(@Param("nickname") String nickname);*/
}
