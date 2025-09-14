package com.marketing.dashboard.dto;

// Channel Data
public class ChannelData {
    private String channel;
    private Double spend;
    private Double revenue;
    private Double roas;
    private Double ctr;
    private Double cpc;
    private Long impressions;
    private Long clicks;

    public ChannelData() {}

    public ChannelData(String channel, Double spend, Double revenue, Long impressions, Long clicks) {
        this.channel = channel;
        this.spend = spend;
        this.revenue = revenue;
        this.impressions = impressions;
        this.clicks = clicks;
        this.roas = spend > 0 ? revenue / spend : 0.0;
        this.ctr = impressions > 0 ? (clicks.doubleValue() / impressions.doubleValue()) * 100 : 0.0;
        this.cpc = clicks > 0 ? spend / clicks : 0.0;
    }

    // Getters and setters
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public Double getSpend() { return spend; }
    public void setSpend(Double spend) { this.spend = spend; }

    public Double getRevenue() { return revenue; }
    public void setRevenue(Double revenue) { this.revenue = revenue; }

    public Double getRoas() { return roas; }
    public void setRoas(Double roas) { this.roas = roas; }

    public Double getCtr() { return ctr; }
    public void setCtr(Double ctr) { this.ctr = ctr; }

    public Double getCpc() { return cpc; }
    public void setCpc(Double cpc) { this.cpc = cpc; }

    public Long getImpressions() { return impressions; }
    public void setImpressions(Long impressions) { this.impressions = impressions; }

    public Long getClicks() { return clicks; }
    public void setClicks(Long clicks) { this.clicks = clicks; }
}
