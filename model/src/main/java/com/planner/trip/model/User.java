package com.planner.trip.model;

import com.planner.trip.code.Auth;
import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "USER")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(unique = true,length = 60)
    private String email;

    @Column(name = "nickname",unique = true,length = 60)
    private String nickname;

    @Column(length = 60)
    private String password;

    @Column(length = 30)
    private String phone;

    @Column(length = 30)
    //@Enumerated(EnumType.STRING)
    private Auth auth;

}
