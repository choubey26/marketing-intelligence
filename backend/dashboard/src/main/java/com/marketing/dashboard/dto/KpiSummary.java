package com.marketing.dashboard.dto;

// KPI Summary
public class KpiSummary {
    private Double totalSpend;
    private Double totalRevenue;
    private Double totalRoas;
    private Integer totalOrders;
    private String spendChangePercent;
    private String revenueChangePercent;
    private String roasChangePercent;
    private String ordersChangePercent;

    public KpiSummary() {}

    public KpiSummary(Double totalSpend, Double totalRevenue, Double totalRoas, Integer totalOrders,
                      String spendChangePercent, String revenueChangePercent, String roasChangePercent, String ordersChangePercent) {
        this.totalSpend = totalSpend;
        this.totalRevenue = totalRevenue;
        this.totalRoas = totalRoas;
        this.totalOrders = totalOrders;
        this.spendChangePercent = spendChangePercent;
        this.revenueChangePercent = revenueChangePercent;
        this.roasChangePercent = roasChangePercent;
        this.ordersChangePercent = ordersChangePercent;
    }

    // Getters and setters
    public Double getTotalSpend() { return totalSpend; }
    public void setTotalSpend(Double totalSpend) { this.totalSpend = totalSpend; }

    public Double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }

    public Double getTotalRoas() { return totalRoas; }
    public void setTotalRoas(Double totalRoas) { this.totalRoas = totalRoas; }

    public Integer getTotalOrders() { return totalOrders; }
    public void setTotalOrders(Integer totalOrders) { this.totalOrders = totalOrders; }

    public String getSpendChangePercent() { return spendChangePercent; }
    public void setSpendChangePercent(String spendChangePercent) { this.spendChangePercent = spendChangePercent; }

    public String getRevenueChangePercent() { return revenueChangePercent; }
    public void setRevenueChangePercent(String revenueChangePercent) { this.revenueChangePercent = revenueChangePercent; }

    public String getRoasChangePercent() { return roasChangePercent; }
    public void setRoasChangePercent(String roasChangePercent) { this.roasChangePercent = roasChangePercent; }

    public String getOrdersChangePercent() { return ordersChangePercent; }
    public void setOrdersChangePercent(String ordersChangePercent) { this.ordersChangePercent = ordersChangePercent; }
}
