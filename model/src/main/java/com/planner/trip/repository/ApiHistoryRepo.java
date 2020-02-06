package com.planner.trip.repository;

import com.planner.trip.model.ApiHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ApiHistoryRepo extends JpaRepository<ApiHistory,Long> {
    Optional<ApiHistory> findByApiNameAndWorkedAtBetween(String apiName, LocalDateTime start, LocalDateTime end);
}
