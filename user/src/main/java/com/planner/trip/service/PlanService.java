package com.planner.trip.service;

import com.planner.trip.model.Plan;
import com.planner.trip.model.User;
import com.planner.trip.payload.PlanSaveRequest;
import com.planner.trip.repository.PlanRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanService {

    private PlanRepo planRepo;

    @Transactional(readOnly = true)
    public List<Plan> findAllAsc(Long idx) {

        return planRepo.findByUserIdx(idx);
    }

    @Transactional
    public Long save(User user, PlanSaveRequest dto) {
        Plan plan = dto.toEntity();
        plan.setUser(user);

        return planRepo.save(plan).getIdx();
    }

}
