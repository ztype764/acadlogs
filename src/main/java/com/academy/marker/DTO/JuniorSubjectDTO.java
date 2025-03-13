package com.academy.marker.DTO;

import lombok.Data;

@Data
public class JuniorSubjectDTO {
    private String name;
    private Integer readingMarks;
    private Integer dictationMarks;
    private Integer oralMarks;
    private Integer writingMarks;
    private Integer maxMarks;
}
