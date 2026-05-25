package com.casekaro.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.casekaro.steps", "com.casekaro.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        monochrome = true
)
public class TestRunner {
}
