package stepDefinition;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

import stepDefinition.Product;

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
	
//	@Before
//	public void siteLaunch()
//	{
//		driver = new ChromeDriver();
//		driver.navigate().to("https://sanity-csm.test8.symplicity.com/manager");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	}
//	
//	@After
//	public void close() throws InterruptedException
//	{
//		Thread.sleep(3000);
//		driver.close();
//	}
	@Before
	public void siteLaunch() throws FileNotFoundException
	{
		Properties prop = new Properties();
		FileReader reader=new FileReader("C:\\Users\\Vignesh.ramanathan\\eclipse-workspace\\NewEclipse\\testingRep\\src\\main\\java\\stepDefinition\\myFile.properties");
		try {
			prop.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = new ChromeDriver();
		System.out.println(prop.getProperty("URL","https://ultimateqa.com/automation/"));
		driver.navigate().to(prop.getProperty("URL","https://ultimateqa.com/automation/"));
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
	
	@Given("I am logged in as {string}")
	public void login(String user) 
	{
		String username = "";
		String password = "iopex@123";
		if(user == "vignesh") {
			username = "vramanathan@sympdebug";
			password = "Vkyking@123a";
		} else if (user == "Manager_Home"){
			username = "home@iopexadmin.com";
		} else {
			username = "testmanager@iopexadmin.com";
		}
		driver.findElement(By.xpath("//input[@class='input-text' and @name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@class='input-password' and @name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Sign In']")).click();
	}

	@Then("I will see {string}")
	public void iWillSee(String text) 
	{
		WebElement textByXapth = driver.findElement(By.xpath("//*[text()='"+text+"']"));
		String actual = "";
		try {
			actual = textByXapth.getText();
		} catch(Exception $e) {
			wait.until(ExpectedConditions.visibilityOf(textByXapth));
			actual = textByXapth.getText();
		}
		if (text.equalsIgnoreCase(actual)) {
			System.out.println(actual);
		}
	}
	
	@When("I navigate to {string}")
	public void iNavigateTo(String path)
	{
		String navMenu[] = path.split(">");
		WebElement mainMenu = driver.findElement(By.xpath("//a[@title='Students']//i[@id='toggler_"+navMenu[0].toLowerCase()+"']"));
		mainMenu.click();
//		WebElement subMenu = driver.findElement(By.xpath("//a[@class='navitem hi no-children']//span[@class='navtext' and text()='"+navMenu[1]+"']"));
//		try {
//			subMenu.click();
//		} catch(Exception $e) {
//			wait.until(ExpectedConditions.elementToBeClickable(subMenu));
//			subMenu.click();
//		}
	}
	
	@And("I search with {string}")
	public void searchWith(String value) 
	{
		WebElement keywordFilter =  driver.findElement(By.xpath("//input[@id='studentfilters_keywords_' and @type='text']"));
		keywordFilter.clear();
		keywordFilter.sendKeys(value);
		driver.findElement(By.xpath("//input [@type='submit' and @value='apply search']")).click();
	}
	
	@And("I click {string} Link")
	public void clickLink(String link) throws Exception
	{
		WebElement ele = driver.findElement(By.linkText(link));
		try {
			ele.click();
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		} catch (NoSuchElementException e) {
			throw new Exception(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@And("I open {string} tab")
	public void iOpenTab(String tab)
	{
		WebElement element = driver.findElement(By.xpath("//div[@class='tab_text' and normalize-space(text())='"+tab+"']"));
		try {
			element.click();
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		}
	}
	
	@And("I impersonateAs {string}")
	public void iImpersonateAs(String user) throws Exception
	{
		this.clickLink(user);
		this.iOpenTab("Login As");
		driver.switchTo().frame("loginas_frame");
		this.clickLink("Open in a separate window");
	}
}
