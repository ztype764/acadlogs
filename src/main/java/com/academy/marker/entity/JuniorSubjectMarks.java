package com.academy.marker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "junior_subject_marks")
public class JuniorSubjectMarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "junior_report_card_id", nullable = false)
    private JuniorReportCard juniorReportCard;

    @Column(nullable = false)
    private String subjectName;

    @Column
    private Integer readingMarks;

    @Column
    private Integer dictationMarks;

    @Column
    private Integer oralMarks;

    @Column
    private Integer writingMarks;

    @Column
    private Integer maxMarks;

    @Column
    private String grade; // Only for Theology
}
