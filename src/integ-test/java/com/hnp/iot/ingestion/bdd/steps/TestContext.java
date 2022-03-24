package com.hnp.iot.ingestion.bdd.steps;

import com.hnp.iot.data.ingestion.IoTIngestionApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@ActiveProfiles("bdd")
@SpringBootTest(classes = {IoTIngestionApplication.class})
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@Slf4j
public class TestContext {

    @Test
    public void testIngestion() {
        log.info("----------------- Started ingestion integration testing -----------------");
    }
}
