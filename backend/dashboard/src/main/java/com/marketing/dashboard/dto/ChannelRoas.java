package com.marketing.dashboard.dto;

// Channel ROAS
public class ChannelRoas {
    private String channel;
    private Double roas;

    public ChannelRoas() {}

    public ChannelRoas(String channel, Double roas) {
        this.channel = channel;
        this.roas = roas;
    }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public Double getRoas() { return roas; }
    public void setRoas(Double roas) { this.roas = roas; }
}
