package com.academy.marker.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JuniorSubjectMarksDTO {
    private String subjectName;
    private Integer readingMarks;
    private Integer dictationMarks;
    private Integer oralMarks;
    private Integer writingMarks;
    private Integer maxMarks;
    private String grade; // For Theology
}
