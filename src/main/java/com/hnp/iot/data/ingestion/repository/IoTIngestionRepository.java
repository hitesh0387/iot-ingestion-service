package com.hnp.iot.data.ingestion.repository;

import com.hnp.iot.data.ingestion.entity.TimeSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository class to perform CRUD operations on the time-series database
 */
public interface IoTIngestionRepository extends JpaRepository<TimeSeries, Long> {

    @Query(value = "Select t from TimeSeries t where readingTimeStamp >= :startTime and readingTimeStamp <= :endTime")
    List<TimeSeries> fetchTimeSeriesData(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
