package com.marketing.dashboard.dto;

import java.time.LocalDate;
import java.util.List;

// Dashboard Overview DTO
public class DashboardOverviewDto {
    private KpiSummary kpis;
    private List<TimeSeriesData> performanceTrends;
    private List<ChannelData> channelDistribution;
    private List<BusinessMetric> businessMetrics;
    private List<ChannelRoas> roasData;

    // Constructors, getters, setters
    public DashboardOverviewDto() {}

    public KpiSummary getKpis() { return kpis; }
    public void setKpis(KpiSummary kpis) { this.kpis = kpis; }

    public List<TimeSeriesData> getPerformanceTrends() { return performanceTrends; }
    public void setPerformanceTrends(List<TimeSeriesData> performanceTrends) { this.performanceTrends = performanceTrends; }

    public List<ChannelData> getChannelDistribution() { return channelDistribution; }
    public void setChannelDistribution(List<ChannelData> channelDistribution) { this.channelDistribution = channelDistribution; }

    public List<BusinessMetric> getBusinessMetrics() { return businessMetrics; }
    public void setBusinessMetrics(List<BusinessMetric> businessMetrics) { this.businessMetrics = businessMetrics; }

    public List<ChannelRoas> getRoasData() { return roasData; }
    public void setRoasData(List<ChannelRoas> roasData) { this.roasData = roasData; }
}

