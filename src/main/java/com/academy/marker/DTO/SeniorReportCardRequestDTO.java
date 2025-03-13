package com.academy.marker.DTO;

import lombok.Data;

import java.util.List;

@Data
public class SeniorReportCardRequestDTO {
    private Long studentId;
    private List<SeniorSubjectDTO> subjects;
    private Integer attendance;
    private String finalVerdict;
}
