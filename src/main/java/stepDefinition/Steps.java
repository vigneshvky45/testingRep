package stepDefinition;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.CaseFormat;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {

	WebDriver driver;
	WebDriverWait wait;

	@Before
	public void siteLaunch() throws FileNotFoundException
	{
		Properties prop = new Properties();
		FileReader reader=new FileReader("src\\main\\java\\stepDefinition\\myFile.properties");
		try {
			prop.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = new ChromeDriver();
		driver.navigate().to(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@After
	public void close() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.close();
	}

	@Given("/^I fill fields$/")
	public void dataTable(DataTable table)
	{
		List<Map<String,String>> data = table.asMaps();
		for (int i=0; i<data.size(); i++) {
			Map<String, String> entry = data.get(i);
			System.out.println(entry.get("value"));
			switch(entry.get("type")) {
			case  "Link":
				driver.findElement(By.linkText(entry.get("value"))).click();
				break;
			case "Textbox":
				WebElement box = driver.findElement(By.xpath("//input[(@type = '"+entry.get("field").toLowerCase()+"' or @type = 'text') and @placeholder='"+entry.get("field")+"']"));
				box.clear();
				box.sendKeys(entry.get("value"));
				break;
			case "Button":
				driver.findElement(By.xpath("//button[@type='submit' and normalize-space(text())='"+entry.get("value")+"']")).click();
				break;
			}
		}
	}

	@When("I click {string} button")
	public void iClickButton(String button)
	{
		driver.findElement(By.xpath("//button[@type='submit' and normalize-space(text())='"+button+"']")).click();
	}

	@When("I click {string} link")
	public void clickByLink(String link) throws Exception
	{
		WebElement linkElement = driver.findElement(By.linkText(link));
		try {
			linkElement.click();
		} catch (Exception $e) {
			throw new Exception("The link is not clickable");
		}
	}

}
