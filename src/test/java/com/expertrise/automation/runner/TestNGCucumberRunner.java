package com.expertrise.automation.utils.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.expertrise.automation.utils.stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        tags = "@placeholder"
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
    // No code needed here – inheritance handles execution
}