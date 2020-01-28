package com.planner.trip.model;

import com.planner.trip.code.Auth;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USER")
public class User {
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
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
