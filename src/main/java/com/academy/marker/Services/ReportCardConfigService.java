package com.academy.marker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReportCardConfigService {

    @Autowired
    private ReportCardConfigRepository configRepository;

    public ReportCardConfig getConfig() {
        return configRepository.findById(1L).orElseGet(() -> {
            ReportCardConfig defaultConfig = new ReportCardConfig();
            defaultConfig.setMinTotalMarks(200);  // Default passing total
            defaultConfig.setMinPassMarks(35);    // Default subject pass marks
            defaultConfig.setDefaultMaxMarks(100); // Default max per subject
            return defaultConfig;
        });
    }

    public ReportCardConfig saveConfig(ReportCardConfig config) {
        return configRepository.save(config);
    }

    public int getMinTotalMarks() {
        return getConfig().getMinTotalMarks();
    }

    public int getMinPassMarks() {
        return getConfig().getMinPassMarks();
    }

    public int getDefaultMaxMarks() {
        return getConfig().getDefaultMaxMarks();
    }
}
