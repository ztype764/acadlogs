package com.academy.marker.Controllers;

import com.academy.marker.DTO.JuniorReportCardDTO;
import com.academy.marker.Services.JuniorReportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/junior-report-cards")
public class JuniorReportCardController {

    private final JuniorReportCardService juniorReportCardService;

    @Autowired
    public JuniorReportCardController(JuniorReportCardService juniorReportCardService) {
        this.juniorReportCardService = juniorReportCardService;
    }

    @PostMapping("/create")
    public String createJuniorReportCard(@ModelAttribute JuniorReportCardDTO dto) {
        return juniorReportCardService.createJuniorReportCard(dto);
    }

    @GetMapping("/{id}")
    public JuniorReportCardDTO getJuniorReportCard(@PathVariable Long id) {
        return juniorReportCardService.getJuniorReportCard(id);
    }

    @PutMapping("/{id}/update")
    public String updateJuniorReportCard(@PathVariable Long id, @ModelAttribute JuniorReportCardDTO dto) {
        return juniorReportCardService.updateJuniorReportCard(id, dto);
    }

    @DeleteMapping("/{id}/delete")
    public String deleteJuniorReportCard(@PathVariable Long id) {
        return juniorReportCardService.deleteJuniorReportCard(id);
    }
}
