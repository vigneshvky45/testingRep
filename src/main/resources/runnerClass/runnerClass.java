import org.testng.annotations.DataProvider;

import io.cucumber.core.snippets.SnippetType;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {src/test/java/Features},
		dryRun = false,
		glue = "Steps",
		snippets = SnippetType.CAMELCASE,
		monochrome = true)

public class runnerClass extends AbstractTestNGCucumberTests{
	@DataProvider
	public void getTestData() {
		
	}
}
