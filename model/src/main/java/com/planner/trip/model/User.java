package com.planner.trip.model;

import com.planner.trip.code.Auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(unique = true, length = 60, nullable = false)
    private String email;

    @Column(name = "nickname", unique = true, length = 60, nullable = false)
    private String nickname;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String phone;

    @Column(length = 30)
    //@Enumerated(EnumType.STRING)
    private Auth auth;

}
