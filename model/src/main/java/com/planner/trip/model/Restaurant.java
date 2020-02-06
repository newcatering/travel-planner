package com.planner.trip.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="RESTAURANT")
@ToString
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title;
    private String link;
    private String category;
    private String telephone;
    private String address;
    @Column(name = "road_address")
    private String roadAddress;
    private Long Latitude;//mapy
    private Long longitude;//mapx
    @Column(name = "addr_idx")
    private Long addrIdx; //시군구
    private Integer rank; // 코멘트 순위
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
