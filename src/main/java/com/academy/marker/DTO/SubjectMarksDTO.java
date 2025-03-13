package com.academy.marker.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectMarksDTO {
    private String subjectName;
    private Integer marksObtained;
    private Integer maxMarks;
    private String grade; // Only applicable for Theology
}
