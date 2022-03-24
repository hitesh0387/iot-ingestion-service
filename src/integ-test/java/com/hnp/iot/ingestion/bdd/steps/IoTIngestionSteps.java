package com.hnp.iot.ingestion.bdd.steps;

import com.hnp.iot.data.ingestion.entity.TimeSeries;
import com.hnp.iot.data.ingestion.service.IoTIngestionService;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class IoTIngestionSteps implements En {

    private final String[] sensorIds = new String[]{"000A", "000B", "000C", "000D"};
    private static final float MINIMUM_WIND_SPEED = 0.1f;
    private static final float MAXIMUM_WIND_SPEED = 10.0f;
    private static final float MINIMUM_WIND_DIRECTION = 0.0f;
    private static final float MAXIMUM_WIND_DIRECTION = 360.0f;
    private static final float MINIMUM_METHANE_VALUE = 0.4f;
    private static final float MAXIMUM_METHANE_VALUE = 1000.0f;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaTemplate<String, TimeSeries> kafkaTemplate;

    @Autowired
    private IoTIngestionService ioTIngestionService;

    @Value("${iot.ingestion.kafka.topic}")
    private String kafkaTopic;

    public IoTIngestionSteps() {

        Given("^IoT data is produced successfully$", this::mockKafkaProducer);
        When("^IoT listener consumes the message$", () -> Thread.sleep(1000));
        Then("^Verify that the data is ingested successfully$", this::verifyTheCountOfConsumedRecords);
    }

    private void mockKafkaProducer() {
        log.info("----------------- Mocking kafka Producer -----------------");

        List<ListenableFuture<SendResult<String, TimeSeries>>> futures = new ArrayList<>(this.sensorIds.length);

        Arrays.stream(this.sensorIds).forEach((sensorId) -> {
            TimeSeries timeSeries = this.mockTimeSeriesData(sensorId);

            ListenableFuture<SendResult<String, TimeSeries>> future = this.kafkaTemplate.send(
                    MessageBuilder
                            .withPayload(timeSeries).
                            setHeader(KafkaHeaders.TOPIC, this.kafkaTopic)
                            .build());

            future.addCallback(
                    result -> log.info("----------------- Successfully published the data for sensor ID: {} -----------------", timeSeries.getSensorId()),
                    error -> {
                        Assert.fail("Failed to publish the  data for sensor ID: " + timeSeries.getSensorId());
                        log.error("----------------- Error while publishing the data -----------------", error);
                    });
        });
    }

    private TimeSeries mockTimeSeriesData(String sensorId) {
        TimeSeries timeSeries = new TimeSeries();
        timeSeries.setSensorId(sensorId);
        timeSeries.setMethaneConcentration(this.generateRandomNumber(MINIMUM_METHANE_VALUE, MAXIMUM_METHANE_VALUE));
        timeSeries.setWindSpeed(this.generateRandomNumber(MINIMUM_WIND_SPEED, MAXIMUM_WIND_SPEED));
        timeSeries.setWindDirection(this.generateRandomNumber(MINIMUM_WIND_DIRECTION, MAXIMUM_WIND_DIRECTION));
        timeSeries.setReadingTimeStamp(LocalDateTime.now(ZoneId.of("UTC")));

        return timeSeries;
    }

    private float generateRandomNumber(float min, float max) {
        return new SecureRandom().nextFloat() * (max - min) + min;
    }

    private void verifyTheCountOfConsumedRecords() {

        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("UTC"));

        LocalDateTime startTime = currentTime.minus(20, ChronoUnit.SECONDS);
        LocalDateTime endTime = currentTime.minus(-20, ChronoUnit.SECONDS);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<TimeSeries> timeSeriesData = this.ioTIngestionService.fetchTimeSeriesData(startTime, endTime);

        Assert.assertEquals(this.sensorIds.length, timeSeriesData.size());
    }
}
