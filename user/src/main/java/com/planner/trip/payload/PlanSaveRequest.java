package com.planner.trip.payload;

import com.planner.trip.model.Plan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlanSaveRequest {

    private String title;

    private Integer people;

    private Boolean sharing;

    private String comment;

    public Plan toEntity() {
        return Plan.builder()
                .title(title)
                .people(people)
                .sharing(sharing)
                .comment(comment)
                .build();
    }
}
