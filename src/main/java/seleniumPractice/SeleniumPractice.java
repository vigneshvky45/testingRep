package seleniumPractice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class SeleniumPractice {

	WebDriver driver;
	RemoteWebDriver driver1;
	WebDriverWait wait;

	public long takeSnap(){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			FileUtils.copyFile(scrShot.getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

	public void takeSnaponElement(WebElement ele) throws IOException{
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			String screenshotBase64 = ((TakesScreenshot) ele).getScreenshotAs(OutputType.BASE64);
			FileOutputStream out = new FileOutputStream("./reports/images/baseimg.png");
			out.write(org.apache.commons.codec.binary.Base64.decodeBase64(screenshotBase64));
			System.out.println("This is the "+screenshotBase64);
			out.close();
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		}
	}

	public void login()
	{
		driver = new ChromeDriver();
		driver.navigate().to("https://www.google.com");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void quit() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.quit();
	}

	public void googleShadowRoot()
	{
		WebElement shadowHost = driver.findElement(By.xpath("//cr-iconset[@name='cr20']"));

		if (!shadowHost.isEnabled()) {
			System.out.println("not exist");
		}
	}

	public void clickByLink(String link) throws IOException
	{
		WebElement linkElement = driver.findElement(By.linkText(link));
		this.takeSnaponElement(linkElement);
		try {
			linkElement.click();
			this.takeSnap();
		} catch (Exception $e) {
			this.takeSnap();
		}
	}

	public void selectByIndex(int index, String selected)
	{
		Select se = new Select(driver.findElement(By.xpath("//select")));
		se.selectByIndex(index);
		String actual = se.toString();
		System.out.println(actual);
		if (selected.equals(actual)) {
			this.takeSnap();
		} 
	}

	public void getTextFromDroplist()
	{
		Select se = new Select(driver.findElement(By.xpath("//select")));
		String text = se.getFirstSelectedOption().getText();
		System.out.println(text);
	}

	public static void main(String[] args) throws InterruptedException, IOException {


	}
}


