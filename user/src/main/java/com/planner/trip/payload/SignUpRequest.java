package com.planner.trip.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {

    private String email;

    private String nickname;

    private String password;

    private String phone;

}
