package com.academy.marker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class ReportCardConfigController {

    @Autowired
    private ReportCardConfigService configService;

    // Fetch current config
    @GetMapping
    public ReportCardConfig getConfig() {
        return configService.getConfig();
    }

    // Update config
    @PutMapping
    public ReportCardConfig updateConfig(@RequestBody ReportCardConfig config) {
        return configService.saveConfig(config);
    }
}
