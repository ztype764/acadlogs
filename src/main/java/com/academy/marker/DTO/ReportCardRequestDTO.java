package com.academy.marker.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ReportCardRequestDTO {
    private Long studentId;
    private String examType;
    private List<SubjectMarksDTO> subjectMarks;
    private String finalVerdict;
}
