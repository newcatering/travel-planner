package com.planner.trip.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="PLAN")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String title;

    private Integer people;// 인원수 ( 네이밍 수정 필요)

    private Boolean sharing;//공유 여부

    private String comment;

    // ManyToOne 다대일 관계 매핑, fetch 기본전략은 EAGER
    // JoinColumn 외래키 이름 지정 (생략시 외래키로 매핑되는 컬럼 이름명 + '_' +참조하는 테이블의 컬럼명)
    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @Builder
    public Plan(String title, Integer people, Boolean sharing, String comment, User user) {
        this.title = title;
        this.people = people;
        this.sharing = sharing;
        this.comment = comment;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
