package com.hnp.iot.data.ingestion.repository;

import com.hnp.iot.data.ingestion.entity.TimeSeries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class to perform CRUD operations on the time-series database
 */
public interface IoTIngestionRepository extends JpaRepository<TimeSeries, Long> {
}
