package com.planner.trip.code;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200,"요청 성공"),
    UNAUTHORIZED(300,"접근 거부"),
    INSUFFICIENT(301,"파라미터 불충분");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
