package com.marketing.dashboard.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "marketing_data")
public class MarketingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "tactic", nullable = false)
    private String tactic;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "campaign", nullable = false)
    private String campaign;

    @Column(name = "channel", nullable = false)
    private String channel;

    @Column(name = "impressions", nullable = false)
    private Long impressions;

    @Column(name = "clicks", nullable = false)
    private Long clicks;

    @Column(name = "spend", nullable = false)
    private Double spend;

    @Column(name = "attributed_revenue", nullable = false)
    private Double attributedRevenue;

    // Default constructor
    public MarketingData() {}

    // Constructor
    public MarketingData(LocalDate date, String tactic, String state, String campaign,
                         String channel, Long impressions, Long clicks, Double spend,
                         Double attributedRevenue) {
        this.date = date;
        this.tactic = tactic;
        this.state = state;
        this.campaign = campaign;
        this.channel = channel;
        this.impressions = impressions;
        this.clicks = clicks;
        this.spend = spend;
        this.attributedRevenue = attributedRevenue;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getTactic() { return tactic; }
    public void setTactic(String tactic) { this.tactic = tactic; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCampaign() { return campaign; }
    public void setCampaign(String campaign) { this.campaign = campaign; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public Long getImpressions() { return impressions; }
    public void setImpressions(Long impressions) { this.impressions = impressions; }

    public Long getClicks() { return clicks; }
    public void setClicks(Long clicks) { this.clicks = clicks; }

    public Double getSpend() { return spend; }
    public void setSpend(Double spend) { this.spend = spend; }

    public Double getAttributedRevenue() { return attributedRevenue; }
    public void setAttributedRevenue(Double attributedRevenue) { this.attributedRevenue = attributedRevenue; }

    // Calculated fields
    public Double getRoas() {
        return spend > 0 ? attributedRevenue / spend : 0.0;
    }

    public Double getCtr() {
        return impressions > 0 ? (clicks.doubleValue() / impressions.doubleValue()) * 100 : 0.0;
    }

    public Double getCpc() {
        return clicks > 0 ? spend / clicks : 0.0;
    }
}