package com.planner.trip.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ADDRESS")
@ToString
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String sido;
    private String sigungu;
    private String dong;
    @Column(name="worked_at")
    private LocalDateTime workedAt;
}
