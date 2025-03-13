package com.academy.marker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/senior-report")
public class SeniorReportCardController {

    @Autowired
    private SeniorReportCardService reportCardService;

    // Get report card by student ID
    @GetMapping("/{studentId}")
    public Object getReportCard(@PathVariable Long studentId) {
        return reportCardService.getReportCardByStudentId(studentId)
                .orElse("Report card not found");
    }

    // Save or update report card using @ModelAttribute
    @PostMapping
    public String saveReportCard(@ModelAttribute SeniorReportCardRequestDTO requestDTO) {
        reportCardService.saveReportCard(requestDTO);
        return "Report card saved successfully!";
    }
}
