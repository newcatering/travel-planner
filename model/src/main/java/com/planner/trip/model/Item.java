package com.planner.trip.model;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name="dest_idx")
    private Long destIdx;

    private String title;

    private Boolean confirm;

    @Builder
    public Item(String title, Boolean check) {
        this.title = title;
        this.confirm = check;
    }
}
