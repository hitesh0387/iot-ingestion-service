package com.hnp.iot.ingestion.bdd.steps;

import com.hnp.iot.data.ingestion.IoTIngestionApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@CucumberContextConfiguration
@SpringBootTest(classes = {IoTIngestionApplication.class})
@ActiveProfiles("bdd")
public class TestContext {

    @Test
    public void testIngestion() {
        System.out.println("Testing");
    }
}
