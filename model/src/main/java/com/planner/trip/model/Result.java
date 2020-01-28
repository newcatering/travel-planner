package com.planner.trip.model;

import com.planner.trip.code.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Result {
    public ResultCode code;
    public Object data;
}