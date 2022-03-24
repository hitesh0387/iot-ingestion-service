package com.hnp.iot.data.ingestion.service;

import com.hnp.iot.data.ingestion.entity.TimeSeries;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service to process, store & retrieve the time-series data
 */
public interface IoTIngestionService {

    /**
     * Processes and stores the incoming TimeSeries payload
     *
     * @param timeSeries - Time series data from the IoT Sensor
     * @return - Saved data
     */
    TimeSeries ingestIoTData(TimeSeries timeSeries);

    /**
     * Fetch the time series data in the range
     *
     * @param startTime UTC start Time in format YYYY-MM-dd HH:mm:ss
     * @param endTime   UTC end Time in format YYYY-MM-dd HH:mm:ss
     * @return time series data in the given time range
     */
    List<TimeSeries> fetchTimeSeriesData(LocalDateTime startTime, LocalDateTime endTime);
}
