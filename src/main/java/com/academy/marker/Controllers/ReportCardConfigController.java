package com.academy.marker.Controllers;

import com.academy.marker.DTO.ReportCardConfigDTO;
import com.academy.marker.Services.ReportCardConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report-card-config")
public class ReportCardConfigController {

    private final ReportCardConfigService reportCardConfigService;

    @Autowired
    public ReportCardConfigController(ReportCardConfigService reportCardConfigService) {
        this.reportCardConfigService = reportCardConfigService;
    }

    @PostMapping("/update")
    public String updateReportCardConfig(@ModelAttribute ReportCardConfigDTO dto) {
        return reportCardConfigService.updateReportCardConfig(dto);
    }

    @GetMapping
    public ReportCardConfigDTO getReportCardConfig() {
        return reportCardConfigService.getReportCardConfig();
    }
}
