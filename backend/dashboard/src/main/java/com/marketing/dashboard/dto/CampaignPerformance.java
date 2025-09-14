package com.marketing.dashboard.dto;

// Campaign Performance
public class CampaignPerformance {
    private String campaign;
    private String channel;
    private Double spend;
    private Double revenue;
    private Double roas;
    private Long impressions;
    private Long clicks;

    public CampaignPerformance() {}

    public CampaignPerformance(String campaign, String channel, Double spend, Double revenue, Long impressions, Long clicks) {
        this.campaign = campaign;
        this.channel = channel;
        this.spend = spend;
        this.revenue = revenue;
        this.impressions = impressions;
        this.clicks = clicks;
        this.roas = spend > 0 ? revenue / spend : 0.0;
    }

    // Getters and setters
    public String getCampaign() { return campaign; }
    public void setCampaign(String campaign) { this.campaign = campaign; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public Double getSpend() { return spend; }
    public void setSpend(Double spend) { this.spend = spend; }

    public Double getRevenue() { return revenue; }
    public void setRevenue(Double revenue) { this.revenue = revenue; }

    public Double getRoas() { return roas; }
    public void setRoas(Double roas) { this.roas = roas; }

    public Long getImpressions() { return impressions; }
    public void setImpressions(Long impressions) { this.impressions = impressions; }

    public Long getClicks() { return clicks; }
    public void setClicks(Long clicks) { this.clicks = clicks; }
}
