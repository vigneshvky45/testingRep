package runnerClass;

import java.io.File;

import org.testng.annotations.AfterClass;

import com.vimalselvam.cucumber.listener.Reporter;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/main/java/features"},
		glue = {"stepDefinition"},
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class runner extends AbstractTestNGCucumberTests {
	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File("src//main//resources//extent-config.xml"));
		System.out.println(Reporter.getExtentHtmlReport().getReport().getStatus());
	}

}
