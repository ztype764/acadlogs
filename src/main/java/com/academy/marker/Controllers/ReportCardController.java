package com.academy.marker.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report-cards")
public class ReportCardController {

    @Autowired
    private ReportCardService reportCardService;

    @PostMapping
    public ReportCard createReportCard(@ModelAttribute ReportCardRequestDTO reportCardDTO, HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        if (!"TEACHER".equals(role)) {
            throw new RuntimeException("Only teachers can manage report cards");
        }
        return reportCardService.createReportCard(reportCardDTO);
    }

    @GetMapping("/{studentId}")
    public List<ReportCard> getStudentReportCards(@PathVariable Long studentId) {
        return reportCardService.getStudentReportCards(studentId);
    }
}
