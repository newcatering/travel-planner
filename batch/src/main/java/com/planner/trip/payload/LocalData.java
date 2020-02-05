package com.planner.trip.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.planner.trip.json.util.DateDeserializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class LocalData {
    @JsonDeserialize(using = DateDeserializer.class)//deserialize 포맷터
    private LocalDateTime lastBuildDate;
    private int total;
    private int start;
    private int display;
    private items[] items;

}
