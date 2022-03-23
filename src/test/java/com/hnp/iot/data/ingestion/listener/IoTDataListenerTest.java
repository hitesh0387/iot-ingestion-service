package com.hnp.iot.data.ingestion.listener;

import com.hnp.iot.data.ingestion.entity.TimeSeries;
import com.hnp.iot.data.ingestion.service.IoTIngestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IoTDataListenerTest {

    @Mock
    private IoTIngestionService ioTIngestionService;

    @InjectMocks
    private IoTDataListener ioTDataListener;

    @Test
    public void testSuccessfulMessageReception() {

        Message<TimeSeries> message = MessageBuilder.withPayload(this.mockTimeSeries()).build();

        when(this.ioTIngestionService.ingestIoTData(any(TimeSeries.class))).thenReturn(this.mockTimeSeries());

        this.ioTDataListener.onData(message);

        verify(this.ioTIngestionService, times(1)).ingestIoTData(any(TimeSeries.class));
    }

    @Test
    public void testExceptionScenario() throws InterruptedException {

        Message<TimeSeries> message = MessageBuilder.withPayload(this.mockTimeSeries()).build();

        when(this.ioTIngestionService.ingestIoTData(any(TimeSeries.class))).thenThrow(new IllegalStateException("Testing exception"));

        this.ioTDataListener.onData(message);
        Thread.sleep(10);
        verify(this.ioTIngestionService, times(1)).ingestIoTData(any(TimeSeries.class));
    }

    private TimeSeries mockTimeSeries() {
        TimeSeries timeSeries = new TimeSeries();
        timeSeries.setId(1L);
        timeSeries.setSensorId("04ef");
        timeSeries.setWindDirection(115.0f);
        timeSeries.setWindSpeed(1.2f);
        timeSeries.setMethaneConcentration(10.12f);
        timeSeries.setReadingTimeStamp(LocalDateTime.now(ZoneId.of("UTC")));
        return timeSeries;
    }
}
