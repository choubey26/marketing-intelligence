package com.marketing.dashboard.config;

import com.marketing.dashboard.service.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CsvReaderService csvReaderService;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("Loading initial data from CSV files...");
            csvReaderService.loadAllData();
            System.out.println("Initial data loading completed successfully!");
        } catch (Exception e) {
            System.err.println("Warning: Could not load initial data from CSV files: " + e.getMessage());
            System.err.println("Make sure CSV files are placed in src/main/resources/data/ directory");
        }
    }
}