package com.academy.marker.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDTO {
    private Long studentId;
    private Integer totalDays;
    private Integer daysPresent;
}
