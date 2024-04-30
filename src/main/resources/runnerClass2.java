import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {src/test/java/Features},
		dryRun = false,
		glue = "Steps",
		tags = {"@smoke and @reg"},
		snippets = SnippetType.CAMELCASE,
		monochrome = true)

public class runnerClass2 extends AbstractTestNGCucumberTests{
	public class FeatureRunnerTest {

}
