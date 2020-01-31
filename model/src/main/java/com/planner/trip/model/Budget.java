package com.planner.trip.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table("BUDGET")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(name = "dest_idx")
    private Long destIdx;//destination ID
    @Column(name ="expected_cost")
    private Long expectedCost;//예상 비용

}
