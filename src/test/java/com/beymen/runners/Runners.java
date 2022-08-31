package com.beymen.runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin ={
                "json:target/cucumber.json",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",

        },
        features = "src/test/resources",
        glue = "com/beymen/step_definitions",
        dryRun = true,
        tags = "@Wip"
)

public class Runners {


}
