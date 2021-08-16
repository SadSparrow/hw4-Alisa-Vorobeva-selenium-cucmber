package org.sparrow.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"org.sparrow.framework.util.MyAllureListener"},
        glue = {"org.sparrow.framework.steps"},
        features = {"src/test/resources/"},
        tags = "@regress"
)
public class CucumberRunner {
}