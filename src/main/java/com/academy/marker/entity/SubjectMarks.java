package com.academy.marker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "subject_marks")
public class SubjectMarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_card_id", nullable = false)
    private ReportCard reportCard;

    @Column(nullable = false)
    private String subjectName;

    @Column
    private Integer marksObtained;

    @Column
    private Integer maxMarks;

    @Column
    private String grade; // Only used for Theology
}
