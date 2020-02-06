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
@Table(name="NAVER_PLACE_HISTORY")
public class NaverPlaceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @CreationTimestamp
    @Column(name ="created_at")
    private LocalDateTime createdAt;
    @Column(name ="updated_at")
    private LocalDateTime updatedAt;
    private String district; //시군구 등
    @Column(name = "addr_idx")
    private Long addrIdx;
    private String keyword; // 검색어
    private Integer total;
    private Integer stored;
}
