package stepDefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.CaseFormat;

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
	public void siteLaunch()
	{
		driver = new ChromeDriver();
		driver.navigate().to("https://sanity-csm.test8.symplicity.com/manager");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@After
	public void close() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.close();
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
	public void clickLink(String link)
	{
		driver.findElement(By.linkText(link)).click();
	}
	
	@And("I open {string} tab")
	public void iOpenTab(String tab)
	{
		driver.findElement(By.xpath("//div[@class='tab_text' and normalize-space(text())='"+tab+"']")).click();
	}
	
	@And("I impersonateAs {string}")
	public void iImpersonateAs(String user)
	{
		this.clickLink(user);
		this.iOpenTab("Login As");
		driver.switchTo().frame("loginas_frame");
		this.clickLink("Open in a separate window");
	}
}
