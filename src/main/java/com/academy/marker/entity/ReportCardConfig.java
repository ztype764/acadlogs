package com.academy.marker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ReportCardConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Minimum total marks required to pass the exam
    private Integer minTotalMarks;

    // Minimum marks per subject required to pass
    private Integer minPassMarks;

    // Default maximum marks for subjects (customizable)
    private Integer defaultMaxMarks;
}
