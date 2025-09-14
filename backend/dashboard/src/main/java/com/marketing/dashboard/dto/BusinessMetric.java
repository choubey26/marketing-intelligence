package com.marketing.dashboard.dto;

// Business Metric
public class BusinessMetric {
    private String date;
    private Integer orders;
    private Integer newCustomers;

    public BusinessMetric() {}

    public BusinessMetric(String date, Integer orders, Integer newCustomers) {
        this.date = date;
        this.orders = orders;
        this.newCustomers = newCustomers;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Integer getOrders() { return orders; }
    public void setOrders(Integer orders) { this.orders = orders; }

    public Integer getNewCustomers() { return newCustomers; }
    public void setNewCustomers(Integer newCustomers) { this.newCustomers = newCustomers; }
}
