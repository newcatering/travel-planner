package com.planner.trip.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name="BUDGET")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "dest_idx")
    private Long destIdx;//destination ID

    @Column(name ="expected_cost")
    private Long expectedCost;//예상 비용

    @Builder
    public Budget(Long expectedCost) {
        this.expectedCost = expectedCost;
    }
}
