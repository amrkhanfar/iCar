package icartest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="use_cases",
        glue = "icartest"

        )
public class AcceptanceTest {

}
