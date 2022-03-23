package com.hnp.iot.data.ingestion.listener;

import com.hnp.iot.data.ingestion.entity.TimeSeries;
import com.hnp.iot.data.ingestion.service.IoTIngestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@AllArgsConstructor
@Slf4j
public class IoTDataListener {

    private final IoTIngestionService ioTIngestionService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @KafkaListener(topics = "${iot.ingestion.kafka.topic}", groupId = "${iot.ingestion.kafka.consumer-group-id}")
    public void onData(Message<TimeSeries> timeSeriesMessage) {
        this.executorService.submit(() -> this.ingestIoTData(timeSeriesMessage));
    }

    private void ingestIoTData(Message<TimeSeries> timeSeriesMessage) {
        try {
            TimeSeries savedData = this.ioTIngestionService.ingestIoTData(timeSeriesMessage.getPayload());
            log.debug("---------- Saved the data -------------: {}", savedData);
        } catch (Exception e) {
            log.error("Failed to ingest IoT Data", e);
        }
    }
}
