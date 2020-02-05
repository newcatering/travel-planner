package com.planner.trip.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    private String email;

    private String nickname;

    private String password;

    private String phone;

}
