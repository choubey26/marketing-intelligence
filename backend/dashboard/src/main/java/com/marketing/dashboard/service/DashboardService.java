package com.marketing.dashboard.service;

import com.marketing.dashboard.dto.*;
import com.marketing.dashboard.entity.BusinessData;
import com.marketing.dashboard.entity.MarketingData;
import com.marketing.dashboard.repository.BusinessDataRepository;
import com.marketing.dashboard.repository.MarketingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private MarketingDataRepository marketingDataRepository;

    @Autowired
    private BusinessDataRepository businessDataRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DashboardOverviewDto getDashboardOverview(int days) {
        LocalDate startDate = LocalDate.now().minusDays(days);
        DashboardOverviewDto overview = new DashboardOverviewDto();

        // Get KPI summary
        overview.setKpis(getKpiSummary(startDate));

        // Get performance trends
        overview.setPerformanceTrends(getPerformanceTrends(startDate));

        // Get channel distribution
        overview.setChannelDistribution(getChannelDistribution(startDate));

        // Get business metrics
        overview.setBusinessMetrics(getBusinessMetrics(startDate));

        // Get ROAS data
        overview.setRoasData(getChannelRoasData(startDate));

        return overview;
    }

    private KpiSummary getKpiSummary(LocalDate startDate) {
        List<MarketingData> marketingData = marketingDataRepository.findLastNDays(startDate);
        List<BusinessData> businessData = businessDataRepository.findLastNDays(startDate);

        // Calculate totals
        Double totalSpend = marketingData.stream().mapToDouble(MarketingData::getSpend).sum();
        Double totalRevenue = marketingData.stream().mapToDouble(MarketingData::getAttributedRevenue).sum();
        Integer totalOrders = businessData.stream().mapToInt(BusinessData::getOrders).sum();
        Double totalRoas = totalSpend > 0 ? totalRevenue / totalSpend : 0.0;

        // Calculate previous period for comparison
        LocalDate previousStartDate = startDate.minusDays(startDate.until(LocalDate.now()).getDays());
        List<MarketingData> previousMarketingData = marketingDataRepository.findByDateBetween(previousStartDate, startDate.minusDays(1));
        List<BusinessData> previousBusinessData = businessDataRepository.findByDateBetween(previousStartDate, startDate.minusDays(1));

        Double prevTotalSpend = previousMarketingData.stream().mapToDouble(MarketingData::getSpend).sum();
        Double prevTotalRevenue = previousMarketingData.stream().mapToDouble(MarketingData::getAttributedRevenue).sum();
        Integer prevTotalOrders = previousBusinessData.stream().mapToInt(BusinessData::getOrders).sum();
        Double prevTotalRoas = prevTotalSpend > 0 ? prevTotalRevenue / prevTotalSpend : 0.0;

        // Calculate percentage changes
        String spendChange = calculatePercentageChange(totalSpend, prevTotalSpend);
        String revenueChange = calculatePercentageChange(totalRevenue, prevTotalRevenue);
        String ordersChange = calculatePercentageChange(totalOrders.doubleValue(), prevTotalOrders.doubleValue());
        String roasChange = calculatePercentageChange(totalRoas, prevTotalRoas);

        return new KpiSummary(totalSpend, totalRevenue, totalRoas, totalOrders,
                spendChange, revenueChange, roasChange, ordersChange);
    }

    private List<TimeSeriesData> getPerformanceTrends(LocalDate startDate) {
        List<Object[]> dailyTotals = marketingDataRepository.getDailyTotals(startDate);

        return dailyTotals.stream()
                .map(row -> new TimeSeriesData(
                        ((LocalDate) row[0]).format(DATE_FORMATTER),
                        (Double) row[1], // spend
                        (Double) row[2]  // revenue
                ))
                .collect(Collectors.toList());
    }

    private List<ChannelData> getChannelDistribution(LocalDate startDate) {
        List<Object[]> channelAggregates = marketingDataRepository.getChannelAggregates(startDate);

        return channelAggregates.stream()
                .map(row -> new ChannelData(
                        (String) row[0],    // channel
                        (Double) row[1],    // spend
                        (Double) row[2],    // revenue
                        (Long) row[3],      // impressions
                        (Long) row[4]       // clicks
                ))
                .collect(Collectors.toList());
    }

    private List<BusinessMetric> getBusinessMetrics(LocalDate startDate) {
        List<BusinessData> businessData = businessDataRepository.findLastNDays(startDate);

        return businessData.stream()
                .map(data -> new BusinessMetric(
                        data.getDate().format(DATE_FORMATTER),
                        data.getOrders(),
                        data.getNewCustomers()
                ))
                .collect(Collectors.toList());
    }

    private List<ChannelRoas> getChannelRoasData(LocalDate startDate) {
        List<Object[]> channelAggregates = marketingDataRepository.getChannelAggregates(startDate);

        return channelAggregates.stream()
                .map(row -> {
                    String channel = (String) row[0];
                    Double spend = (Double) row[1];
                    Double revenue = (Double) row[2];
                    Double roas = spend > 0 ? revenue / spend : 0.0;
                    return new ChannelRoas(channel, roas);
                })
                .collect(Collectors.toList());
    }

    public List<ChannelData> getChannelAnalytics(int days) {
        LocalDate startDate = LocalDate.now().minusDays(days);
        return getChannelDistribution(startDate);
    }

    public List<CampaignPerformance> getCampaignAnalytics(int days) {
        LocalDate startDate = LocalDate.now().minusDays(days);
        List<Object[]> campaignAggregates = marketingDataRepository.getCampaignAggregates(startDate);

        return campaignAggregates.stream()
                .map(row -> new CampaignPerformance(
                        (String) row[0],    // campaign
                        (String) row[1],    // channel
                        (Double) row[2],    // spend
                        (Double) row[3],    // revenue
                        (Long) row[4],      // impressions
                        (Long) row[5]       // clicks
                ))
                .collect(Collectors.toList());
    }

    private String calculatePercentageChange(Double current, Double previous) {
        if (previous == null || previous == 0) return "0.0";
        Double change = ((current - previous) / previous) * 100;
        return String.format("%.1f", change);
    }
}