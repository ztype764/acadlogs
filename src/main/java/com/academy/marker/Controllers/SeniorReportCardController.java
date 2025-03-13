package com.academy.marker.Controllers;

import com.academy.marker.DTO.SeniorReportCardDTO;
import com.academy.marker.Services.SeniorReportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/senior-report-cards")
public class SeniorReportCardController {

    private final SeniorReportCardService seniorReportCardService;

    @Autowired
    public SeniorReportCardController(SeniorReportCardService seniorReportCardService) {
        this.seniorReportCardService = seniorReportCardService;
    }

    @PostMapping("/create")
    public String createSeniorReportCard(@ModelAttribute SeniorReportCardDTO dto) {
        return seniorReportCardService.createSeniorReportCard(dto);
    }

    @GetMapping("/{id}")
    public SeniorReportCardDTO getSeniorReportCard(@PathVariable Long id) {
        return seniorReportCardService.getSeniorReportCard(id);
    }

    @PutMapping("/{id}/update")
    public String updateSeniorReportCard(@PathVariable Long id, @ModelAttribute SeniorReportCardDTO dto) {
        return seniorReportCardService.updateSeniorReportCard(id, dto);
    }

    @DeleteMapping("/{id}/delete")
    public String deleteSeniorReportCard(@PathVariable Long id) {
        return seniorReportCardService.deleteSeniorReportCard(id);
    }
}
