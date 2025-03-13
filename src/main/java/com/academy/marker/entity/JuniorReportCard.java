package com.academy.marker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "junior_report_cards")
public class JuniorReportCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String examType; // Half-Yearly Test, Half-Yearly Exam, Annual Test, Annual Exam

    @OneToMany(mappedBy = "juniorReportCard", cascade = CascadeType.ALL)
    private List<JuniorSubjectMarks> subjectMarks;

    @Column(nullable = false)
    private Integer totalObtainedMarks;

    @Column(nullable = false)
    private Integer totalMaxMarks;

    @Column(nullable = false)
    private String overallGrade;

    @Column(nullable = false)
    private Integer classPosition;

    @Column(nullable = false)
    private Double percentage;

    @Column(nullable = false)
    private Integer attendance;

    @Column(nullable = false)
    private String finalVerdict;

    @Column(nullable = false)
    private Boolean isPassed;


}
