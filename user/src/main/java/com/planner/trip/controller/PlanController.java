package com.planner.trip.controller;

import com.planner.trip.code.ResultCode;
import com.planner.trip.config.security.CustomUserDetails;
import com.planner.trip.model.Plan;
import com.planner.trip.model.User;
import com.planner.trip.payload.PlanSaveRequest;
import com.planner.trip.payload.Result;
import com.planner.trip.service.PlanService;
import com.planner.trip.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/plans")
public class PlanController {

    private PlanService planService;

    private UserService userService;

    @GetMapping("")
    public Result list(@AuthenticationPrincipal CustomUserDetails user) {
        List<Plan> plans = planService.findAllAsc(user.getIdx());

        Result result = Result.builder()
                .code(ResultCode.SUCCESS)
                .data(plans)
                .build();

        return result;
    }

    @PostMapping("")
    public Result save(@RequestParam("userIdx") Long idx, @RequestBody PlanSaveRequest dto) throws Exception {
        User user = userService.findById(idx);

        Long userIdx = planService.save(user, dto);

        Result result = Result.builder()
                .code(ResultCode.SUCCESS)
                .data(userIdx)
                .build();

        return result;
    }

}
