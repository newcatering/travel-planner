package com.planner.trip.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table("PLAN")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column("user_idx")
    private Long userIdx;
    private String title;
    private Integer people;// 인원수 ( 네이밍 수정 필요)
    private Boolean sharing;//공유 여부
    private String comment;

}
