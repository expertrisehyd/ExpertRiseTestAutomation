# ExpertRiseTestAutomation

A small Selenium / Cucumber / TestNG-based test automation project scaffold used to demonstrate running BDD feature files and collecting reports.

## Overview
- Features: src/test/resources/features
- Step definitions: src/test/java/com/expertrise/automation/utils/stepDefinitions
- Test runner: src/test/java/com/expertrise/automation/utils/runner/TestNGCucumberRunner.java (uses TestNG + Cucumber)
- Reports: target/cucumber-reports.html and target/cucumber.json; additional HTML report is generated under target/cucumber-html-reports

## Prerequisites
- JDK 17
- Maven (for running mvn test)
- An IDE (IntelliJ recommended) for running the TestNG runner directly

## Workflow / How tests run
1. Feature files are placed under src/test/resources/features and annotated with Gherkin.
2. The TestNGCucumberRunner (TestNG) picks up features based on the path configured in @CucumberOptions and loads step implementations from the configured glue package.
3. For each Gherkin step Cucumber looks for a matching step definition annotated with io.cucumber.java.en.Given / When / Then in the glue package.
4. Test execution produces JSON and HTML reports in the target/ folder.

## Run tests
- From command line: mvn test
- From IntelliJ: Run the TestNG test class TestNGCucumberRunner (right-click -> Run)

## Troubleshooting
- UndefinedStepException: ensure the runner's glue package matches the package of your step definition classes. For this project the glue is set to `com.expertrise.automation.utils.stepDefinitions`.
- Step annotations must use io.cucumber.java.en.* (Given, When, Then).
- SLF4J logging warnings: "Failed to load class org.slf4j.impl.StaticLoggerBinder" are warnings only. To remove them add a logger binding to the pom (for example `org.slf4j:slf4j-simple` or `ch.qos.logback:logback-classic`).

## Adding a new step
- Create a Java method in a class under the stepDefinitions package and annotate it with the matching Gherkin annotation and expression.


