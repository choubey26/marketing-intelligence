package com.marketing.dashboard.dto;

// Time Series Data
public class TimeSeriesData {
    private String date;
    private Double spend;
    private Double revenue;

    public TimeSeriesData() {}

    public TimeSeriesData(String date, Double spend, Double revenue) {
        this.date = date;
        this.spend = spend;
        this.revenue = revenue;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Double getSpend() { return spend; }
    public void setSpend(Double spend) { this.spend = spend; }

    public Double getRevenue() { return revenue; }
    public void setRevenue(Double revenue) { this.revenue = revenue; }
}
