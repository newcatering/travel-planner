package com.planner.trip.repository;


import com.planner.trip.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepo extends JpaRepository<Plan, Long> {
    List<Plan> findByUserIdx(Long userIdx);
}
