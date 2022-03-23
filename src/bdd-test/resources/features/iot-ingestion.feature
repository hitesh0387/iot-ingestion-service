Feature: Feature to demonstrate that the micro-service can ingest the IoT Data

  Scenario: Verify that the IoT data is ingested successfully
    Given IoT data is produced successfully
    When IoT listener consumes the message
    Then Verify that the data is ingested successfully
    Then clean up the test IoT data