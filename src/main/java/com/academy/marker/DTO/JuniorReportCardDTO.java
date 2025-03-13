package com.academy.marker.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class JuniorReportCardDTO {
    private Long studentId;
    private String examType;
    private List<JuniorSubjectMarksDTO> subjectMarks;
    private Integer totalObtainedMarks;
    private Integer totalMaxMarks;
    private String overallGrade;
    private Integer classPosition;
    private Double percentage;
    private Integer attendance;
    private String finalVerdict;
    private Boolean isPassed;
}
