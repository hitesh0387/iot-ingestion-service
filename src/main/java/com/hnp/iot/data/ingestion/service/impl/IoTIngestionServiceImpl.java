package com.hnp.iot.data.ingestion.service.impl;

import com.hnp.iot.data.ingestion.entity.TimeSeries;
import com.hnp.iot.data.ingestion.repository.IoTIngestionRepository;
import com.hnp.iot.data.ingestion.service.IoTIngestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IoTIngestionServiceImpl implements IoTIngestionService {

    private final IoTIngestionRepository ioTIngestionRepository;

    @Override
    public TimeSeries ingestIoTData(TimeSeries timeSeries) {
        return this.ioTIngestionRepository.save(timeSeries);
    }
}
