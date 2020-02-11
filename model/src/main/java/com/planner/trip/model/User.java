package com.planner.trip.model;

import com.planner.trip.code.Auth;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
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

    @Builder
    public User(String email, String nickname, String password, String phone, Auth auth) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.auth = auth;
    }
}
