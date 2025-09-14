package com.marketing.dashboard.controller;

import com.marketing.dashboard.dto.CampaignPerformance;
import com.marketing.dashboard.dto.ChannelData;
import com.marketing.dashboard.dto.DashboardOverviewDto;
import com.marketing.dashboard.service.CsvReaderService;
import com.marketing.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*") // Allow frontend to access API
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private CsvReaderService csvReaderService;

    @GetMapping("/overview")
    public ResponseEntity<DashboardOverviewDto> getDashboardOverview(
            @RequestParam(defaultValue = "7") int days) {
        DashboardOverviewDto overview = dashboardService.getDashboardOverview(days);
        return ResponseEntity.ok(overview);
    }

    @GetMapping("/channels")
    public ResponseEntity<List<ChannelData>> getChannelAnalytics(
            @RequestParam(defaultValue = "7") int days) {
        List<ChannelData> channels = dashboardService.getChannelAnalytics(days);
        return ResponseEntity.ok(channels);
    }

    @GetMapping("/campaigns")
    public ResponseEntity<List<CampaignPerformance>> getCampaignAnalytics(
            @RequestParam(defaultValue = "7") int days) {
        List<CampaignPerformance> campaigns = dashboardService.getCampaignAnalytics(days);
        return ResponseEntity.ok(campaigns);
    }

    @PostMapping("/load-data")
    public ResponseEntity<String> loadDataFromCsv() {
        try {
            csvReaderService.loadAllData();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error loading data: " + e.getMessage());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Dashboard API is running");
    }
}