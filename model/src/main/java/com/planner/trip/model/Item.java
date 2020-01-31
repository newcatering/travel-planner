package com.planner.trip.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table("ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(name="dest_idx")
    private Long destIdx;
    private String title;
    private Boolean check;

}
