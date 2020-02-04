package com.planner.trip.model;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="PLAN")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name="user_idx")
    private Long userIdx;

    private String title;

    private Integer people;// 인원수 ( 네이밍 수정 필요)

    private Boolean sharing;//공유 여부

    private String comment;

    @Builder
    public Plan(String title, Integer people, Boolean sharing, String comment) {
        this.title = title;
        this.people = people;
        this.sharing = sharing;
        this.comment = comment;
    }
}
