package com.planner.trip.model;

import com.planner.trip.code.DestinationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table("DESTINATION")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "plan_idx")
    private Long planIdx;// 계획표 ID
    private String name;//상호명

    @Column(columnDefinition ="DECIMAL(6,10)")
    private BigDecimal latitude;//위도

    @Column(columnDefinition ="DECIMAL(6,10)")
    private BigDecimal longitude;//경도

    private String addr;//주소

    @Column(name = "sub_addr")
    private String subAddr;//상세주소

    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime;//예정 시간

    private DestinationType type;//방문지 타입

    private Integer order; // 여행 순서
}
