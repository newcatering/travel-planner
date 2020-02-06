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
@Table(name="API_HISTORY")
public class ApiHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @CreationTimestamp
    @Column(name ="worked_at")
    private LocalDateTime workedAt;
    @Column(name ="api_name")
    private String apiName;
    @Column(name ="request_count")
    private int requestCount;
}
