package com.academy.marker.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class SeniorReportCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "seniorReportCard", cascade = CascadeType.ALL)
    private List<SeniorSubject> subjects;

    // Common report card details
    private Integer grandTotal;
    private String overallGrade;
    private Integer positionInClass;
    private Double percentage;
    private Integer attendance;
    private String finalVerdict;
}
