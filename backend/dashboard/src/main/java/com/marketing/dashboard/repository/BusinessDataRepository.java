package com.marketing.dashboard.repository;

import com.marketing.dashboard.entity.BusinessData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BusinessDataRepository extends JpaRepository<BusinessData, Long> {

    // Find data within date range
    List<BusinessData> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Get last N days of data
    @Query("SELECT bd FROM BusinessData bd WHERE bd.date >= :startDate ORDER BY bd.date DESC")
    List<BusinessData> findLastNDays(@Param("startDate") LocalDate startDate);

    // Get totals for date range
    @Query("SELECT SUM(bd.orders), SUM(bd.newOrders), SUM(bd.newCustomers), " +
            "SUM(bd.totalRevenue), SUM(bd.grossProfit) " +
            "FROM BusinessData bd WHERE bd.date >= :startDate")
    Object[] getTotalsFromDate(@Param("startDate") LocalDate startDate);
}