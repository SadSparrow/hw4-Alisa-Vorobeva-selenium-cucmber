package org.sparrow.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        glue = {"org.sparrow.framework.steps"},
        features = {"src/test/resources/"},
        tags = "@regress"
)
public class CucumberRunner {
}