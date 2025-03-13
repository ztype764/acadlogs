package com.academy.marker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "senior_subject_marks")
public class SeniorSubjectMarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "senior_report_card_id", nullable = false)
    private SeniorReportCard seniorReportCard;

    @Column(nullable = false)
    private String subjectName;

    @Column
    private Integer marksObtained;

    @Column
    private Integer maxMarks;
}
