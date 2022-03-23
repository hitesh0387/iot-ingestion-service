package com.hnp.iot.data.ingestion.service;

import com.hnp.iot.data.ingestion.entity.TimeSeries;

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
}
