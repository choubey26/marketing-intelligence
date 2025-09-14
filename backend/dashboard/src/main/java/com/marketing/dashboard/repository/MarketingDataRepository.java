package com.marketing.dashboard.repository;

import com.marketing.dashboard.entity.MarketingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MarketingDataRepository extends JpaRepository<MarketingData, Long> {

    // Find data within date range
    List<MarketingData> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Find data by channel
    List<MarketingData> findByChannel(String channel);

    // Find data by campaign
    List<MarketingData> findByCampaign(String campaign);

    // Get last N days of data
    @Query("SELECT md FROM MarketingData md WHERE md.date >= :startDate ORDER BY md.date DESC")
    List<MarketingData> findLastNDays(@Param("startDate") LocalDate startDate);

    // Get aggregated data by channel
    @Query("SELECT md.channel, SUM(md.spend), SUM(md.attributedRevenue), SUM(md.impressions), SUM(md.clicks) " +
            "FROM MarketingData md WHERE md.date >= :startDate " +
            "GROUP BY md.channel")
    List<Object[]> getChannelAggregates(@Param("startDate") LocalDate startDate);

    // Get aggregated data by campaign
    @Query("SELECT md.campaign, md.channel, SUM(md.spend), SUM(md.attributedRevenue), SUM(md.impressions), SUM(md.clicks) " +
            "FROM MarketingData md WHERE md.date >= :startDate " +
            "GROUP BY md.campaign, md.channel " +
            "ORDER BY SUM(md.attributedRevenue) DESC")
    List<Object[]> getCampaignAggregates(@Param("startDate") LocalDate startDate);

    // Get daily totals for trends
    @Query("SELECT md.date, SUM(md.spend), SUM(md.attributedRevenue), SUM(md.impressions), SUM(md.clicks) " +
            "FROM MarketingData md WHERE md.date >= :startDate " +
            "GROUP BY md.date ORDER BY md.date")
    List<Object[]> getDailyTotals(@Param("startDate") LocalDate startDate);
}