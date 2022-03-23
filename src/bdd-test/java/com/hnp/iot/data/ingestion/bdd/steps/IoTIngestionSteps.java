package com.hnp.iot.data.ingestion.bdd.steps;

import io.cucumber.java8.En;

public class IoTIngestionSteps implements En {

    public IoTIngestionSteps() {
        Given("^IoT data is produced successfully$", () -> {
            System.out.println("Executing the given step");
        });

        When("^IoT listener consumes the message$", () -> {
            System.out.println("Executing the when step");
        });

        Then("^Verify that the data is ingested successfully$", () -> {
            System.out.println("Executing the then step");
        });

        Then("^clean up the test IoT data$", () -> {
            System.out.println("Executing the then clean-up step...");
        });
    }
}
