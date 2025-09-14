package com.marketing.dashboard.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "business_data")
public class BusinessData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false, unique = true)
    private LocalDate date;

    @Column(name = "orders", nullable = false)
    private Integer orders;

    @Column(name = "new_orders", nullable = false)
    private Integer newOrders;

    @Column(name = "new_customers", nullable = false)
    private Integer newCustomers;

    @Column(name = "total_revenue", nullable = false)
    private Double totalRevenue;

    @Column(name = "gross_profit", nullable = false)
    private Double grossProfit;

    @Column(name = "cogs")
    private Double cogs;

    // Default constructor
    public BusinessData() {}

    // Constructor
    public BusinessData(LocalDate date, Integer orders, Integer newOrders, Integer newCustomers,
                        Double totalRevenue, Double grossProfit, Double cogs) {
        this.date = date;
        this.orders = orders;
        this.newOrders = newOrders;
        this.newCustomers = newCustomers;
        this.totalRevenue = totalRevenue;
        this.grossProfit = grossProfit;
        this.cogs = cogs;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getOrders() { return orders; }
    public void setOrders(Integer orders) { this.orders = orders; }

    public Integer getNewOrders() { return newOrders; }
    public void setNewOrders(Integer newOrders) { this.newOrders = newOrders; }

    public Integer getNewCustomers() { return newCustomers; }
    public void setNewCustomers(Integer newCustomers) { this.newCustomers = newCustomers; }

    public Double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }

    public Double getGrossProfit() { return grossProfit; }
    public void setGrossProfit(Double grossProfit) { this.grossProfit = grossProfit; }

    public Double getCogs() { return cogs; }
    public void setCogs(Double cogs) { this.cogs = cogs; }

    // Calculated fields
    public Double getAverageOrderValue() {
        return orders > 0 ? totalRevenue / orders : 0.0;
    }

    public Double getGrossProfitMargin() {
        return totalRevenue > 0 ? (grossProfit / totalRevenue) * 100 : 0.0;
    }
}