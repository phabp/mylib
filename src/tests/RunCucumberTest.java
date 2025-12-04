package tests;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:add_book.feature",   // <– repare: sem “features/”
    glue = {"tests.steps"},
    monochrome = true,
    plugin = {"pretty"}
)
public class RunCucumberTest {
}
