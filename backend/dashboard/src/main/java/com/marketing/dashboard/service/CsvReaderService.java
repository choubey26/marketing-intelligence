package com.marketing.dashboard.service;

import com.marketing.dashboard.entity.BusinessData;
import com.marketing.dashboard.entity.MarketingData;
import com.marketing.dashboard.repository.BusinessDataRepository;
import com.marketing.dashboard.repository.MarketingDataRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReaderService {

    @Autowired
    private MarketingDataRepository marketingDataRepository;

    @Autowired
    private BusinessDataRepository businessDataRepository;

    // Two possible date formats
    private static final DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER1);
        } catch (Exception ignored) {}
        try {
            return LocalDate.parse(dateStr, FORMATTER2);
        } catch (Exception ignored) {}
        throw new IllegalArgumentException("Unrecognized date format: " + dateStr);
    }

    /**
     * Helper to load InputStream either from classpath (JAR) or from local filesystem (for dev).
     */
    private InputStream getCsvStream(String resourcePath) throws Exception {
        // Try classpath
        ClassPathResource resource = new ClassPathResource(resourcePath);
        if (resource.exists()) {
            return resource.getInputStream();
        }

        // Fallback: local filesystem (for dev mode)
        File file = new File("src/main/resources/" + resourcePath);
        if (file.exists()) {
            return new FileInputStream(file);
        }

        throw new FileNotFoundException("CSV file not found in classpath or filesystem: " + resourcePath);
    }

    @Transactional
    public void loadMarketingDataFromCsv(String resourcePath, String channel) {
        try {
            List<MarketingData> marketingDataList = new ArrayList<>();

            try (InputStream is = getCsvStream(resourcePath);
                 InputStreamReader isr = new InputStreamReader(is);
                 CSVReader reader = new CSVReader(isr)) {

                List<String[]> records = reader.readAll();

                // Skip header row if present
                boolean hasHeader = records.size() > 0 && !isNumeric(records.get(0)[4]);
                int startIndex = hasHeader ? 1 : 0;

                for (int i = startIndex; i < records.size(); i++) {
                    String[] record = records.get(i);

                    if (record.length >= 8) {
                        try {
                            MarketingData data = new MarketingData();
                            data.setDate(parseDate(record[0].trim()));
                            data.setTactic(record[1].trim());
                            data.setState(record[2].trim());
                            data.setCampaign(record[3].trim());
                            data.setChannel(channel);
                            data.setImpressions(Long.parseLong(record[4].trim()));
                            data.setClicks(Long.parseLong(record[5].trim()));
                            data.setSpend(Double.parseDouble(record[6].trim()));
                            data.setAttributedRevenue(Double.parseDouble(record[7].trim()));

                            marketingDataList.add(data);
                        } catch (Exception e) {
                            System.err.println("Error parsing row " + i + ": " + e.getMessage());
                        }
                    }
                }
            }

            if (!marketingDataList.isEmpty()) {
                marketingDataRepository.saveAll(marketingDataList);
                System.out.println("Loaded " + marketingDataList.size() + " records for " + channel);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file: " + resourcePath, e);
        }
    }

    @Transactional
    public void loadBusinessDataFromCsv(String resourcePath) {
        try {
            List<BusinessData> businessDataList = new ArrayList<>();

            try (InputStream is = getCsvStream(resourcePath);
                 InputStreamReader isr = new InputStreamReader(is);
                 CSVReader reader = new CSVReader(isr)) {

                List<String[]> records = reader.readAll();

                // Skip header row if present
                boolean hasHeader = records.size() > 0 && !isNumeric(records.get(0)[1]);
                int startIndex = hasHeader ? 1 : 0;

                for (int i = startIndex; i < records.size(); i++) {
                    String[] record = records.get(i);

                    if (record.length >= 6) {
                        try {
                            BusinessData data = new BusinessData();
                            data.setDate(parseDate(record[0].trim()));
                            data.setOrders(Integer.parseInt(record[1].trim()));
                            data.setNewOrders(Integer.parseInt(record[2].trim()));
                            data.setNewCustomers(Integer.parseInt(record[3].trim()));
                            data.setTotalRevenue(Double.parseDouble(record[4].trim()));
                            data.setGrossProfit(Double.parseDouble(record[5].trim()));
                            if (record.length > 6) {
                                data.setCogs(Double.parseDouble(record[6].trim()));
                            }

                            businessDataList.add(data);
                        } catch (Exception e) {
                            System.err.println("Error parsing business data row " + i + ": " + e.getMessage());
                        }
                    }
                }
            }

            if (!businessDataList.isEmpty()) {
                businessDataRepository.saveAll(businessDataList);
                System.out.println("Loaded " + businessDataList.size() + " business records");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading business CSV file: " + resourcePath, e);
        }
    }

    @Transactional
    public void loadAllData() {
        // Clear existing data
        marketingDataRepository.deleteAll();
        businessDataRepository.deleteAll();

        // Load marketing data
        loadMarketingDataFromCsv("data/Facebook.csv", "Facebook");
        loadMarketingDataFromCsv("data/Google.csv", "Google");
        loadMarketingDataFromCsv("data/TikTok.csv", "TikTok");

        // Load business data
        loadBusinessDataFromCsv("data/business.csv");
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
