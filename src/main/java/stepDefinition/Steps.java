package stepDefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
	
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
	public void login(String user) {
		driver.findElement(By.xpath("//input[@class='input-text' and @name='username']")).sendKeys("vramanathan@sympdebug");
		driver.findElement(By.xpath("//input[@class='input-password' and @name='password']")).sendKeys("Vkyking@123a");
		driver.findElement(By.xpath("//input[@type='submit' and @value='Sign In']")).click();
	}

	@Then("I will see {string}")
	public void iWillSee(String text) {
		WebElement textByXapth = driver.findElement(By.xpath("//span[@class='navtext' and text()='"+text+"']"));
		String actual = textByXapth.getText();
		if (text.equalsIgnoreCase(actual)) {
			System.out.println(actual);
		}
		System.out.println("GitPushTest");
	}
}
