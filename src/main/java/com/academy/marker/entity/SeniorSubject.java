package com.academy.marker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SeniorSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Marks obtained in the subject
    private Integer marks;

    // Maximum marks (customizable via API)
    private Integer maxMarks;

    @ManyToOne
    @JoinColumn(name = "senior_report_card_id")
    private SeniorReportCard seniorReportCard;
}
