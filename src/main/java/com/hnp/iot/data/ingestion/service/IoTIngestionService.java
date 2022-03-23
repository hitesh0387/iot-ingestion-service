package com.hnp.iot.data.ingestion.service;

import com.hnp.iot.data.ingestion.entity.TimeSeries;

public interface IoTIngestionService {

    /**
     *
     * @param timeSeries - Time series data from the IoT Sensor
     */
    TimeSeries ingestIoTData(TimeSeries timeSeries);
}
