package stepDefinition;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends Steps{

	WebDriver driver;

	@Before
	public void first()
	{
		
	}
	@After
	public void close() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.close();
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) throws IOException
	{
		if (scenario.isFailed()) {
			File content = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			byte[] byteContent = FileUtils.readFileToByteArray(content);
			scenario.attach(byteContent, "image/png", "image");
		}
	}
}
