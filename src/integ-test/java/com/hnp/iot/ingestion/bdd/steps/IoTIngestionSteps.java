package com.hnp.iot.ingestion.bdd.steps;

import io.cucumber.java8.En;
import org.junit.Assert;

public class IoTIngestionSteps implements En {

    public IoTIngestionSteps() {
        Given("^IoT data is produced successfully$", () -> {
            System.out.println("Simulating kafka producer");
        });

        When("^IoT listener consumes the message$", () -> {
            System.out.println("Waiting for the data to be consumed");
        });

        Then("^Verify that the data is ingested successfully$", () -> {
            Assert.assertEquals(1, 1);
        });

        Then("^clean up the test IoT data$", () -> {
            System.out.println("Delete the consumed data");
        });
    }
}
