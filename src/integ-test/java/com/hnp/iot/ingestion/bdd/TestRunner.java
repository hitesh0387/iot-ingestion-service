package com.hnp.iot.ingestion.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@ActiveProfiles("bdd")
@CucumberOptions(features = {"src/integ-test/resources"},
        glue = {"com.hnp.iot.ingestion.bdd.steps"},
        plugin = {"pretty", "json:build/bdd-reports/cucumber-reports.json", "html:build/bdd-reports/index.html", "junit:build/bdd-reports/feature-overview.html"})
public class TestRunner {
}
