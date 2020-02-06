package com.planner.trip.repository;

import com.planner.trip.model.NaverPlaceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NaverPlaceHistoryRepo extends JpaRepository<NaverPlaceHistory,Long> {
    @Query(value = "SELECT * FROM NAVER_PLACE_HISTORY n ORDER BY n.created_at DESC LIMIT 1",nativeQuery = true)
    Optional<NaverPlaceHistory> findLastJob();
}
