package com.academy.marker.DTO;

import lombok.Data;

import java.util.List;

@Data
public class JuniorReportCardRequestDTO {
    private Long studentId;
    private List<JuniorSubjectDTO> subjects;
    private Integer attendance;
    private String finalVerdict;
}
