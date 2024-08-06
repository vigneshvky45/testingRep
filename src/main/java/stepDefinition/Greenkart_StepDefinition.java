package stepDefinition;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Greenkart_StepDefinition {
	
	WebDriver driver;
	
	@Given("Lanching greenkart site on {string}")
	public void lanuchSiteOnBrowser(String browserName)
	{
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.navigate().to("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@When("I am adding {string} to the cart")
	public void iClickButton(String productName)
	{
		String buttonName = "ADD TO CART";
		WebElement button = driver.findElement(By.xpath("//h4[@class='product-name' and contains(text(),'"+productName+"')]/following::button[@type='button' and text()='"+buttonName+"'][1]"));
		Steps.clickButtonBy(button);
	}

	@When("click the {string} icon")
	public void openTheCart(String iconName)
	{
		WebElement icon = driver.findElement(By.xpath("//img[@alt='"+iconName+"']"));
		Steps.clickButtonBy(icon);
	}

	@When("I am clicking the {string} button")
	public void clickButton(String buttonName)
	{
		WebElement button = driver.findElement(By.xpath("//button[@type='button' and text()='"+buttonName+"']"));
		Steps.clickButtonBy(button);
	}


	@Then("I will see the below details")
	public void  iVerifyOrders(DataTable datatable) throws Exception
	{
		List<List<String>> ordertable = datatable.asLists();
		int row = 1;
		int column = 2;
		int rowcount = driver.findElements(By.xpath("//table/tbody/tr")).size();
		if (ordertable.size()!=rowcount) {
			throw new Exception("Table data mismatch");
		}
		System.out.println(rowcount);
		for (int i = 1; i<ordertable.size(); i++) {
			for (String order : ordertable.get(i)) {
				String elementText = driver.findElement(By.xpath("//table/tbody/tr["+row+"]/td["+column+"]/p")).getText();
				column++;
				if (!order.equalsIgnoreCase(elementText)) {
					String header = driver.findElement(By.xpath("//table/thead/tr/td["+column+"]")).getText();
					throw new Exception("Expected text "+header+" : "+order+" but actual text is "+elementText+"");
				}
			}
			row++;
			column = 2;
		}
	}

}
