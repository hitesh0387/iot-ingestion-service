package com.hnp.iot.data.ingestion.bdd.steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@CucumberContextConfiguration
@SpringBootTest
@ActiveProfiles("bdd")
public class TestContext {
}
