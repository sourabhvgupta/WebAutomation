package com.adobe.main;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;

public class TestClass {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		String url= "https://relstage.typekit.com/login";
		System.setProperty("webdriver.chrome.driver", "C:\\Adobe Work\\Automation\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		// 2. Navigate to identified URL
					driver.navigate().to(url);
					driver.manage().window().maximize();
					
					WebElement element = driver.findElement(By.name("commit"));
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click();", element);
					
					driver.findElement(By.id("adobeid_username")).sendKeys("syed+oobetest@adobetest.com");
					driver.findElement(By.name("password")).click();
									
					executor.executeScript("document.getElementById('adobeid_password').setAttribute('value', 'P@$$w0rd');");
					
					driver.findElement(By.id("sign_in")).click();
					driver.findElement(By.id("adobeid_callback")).submit();
					
					driver.findElement(By.name("client_redirect")).submit();
					//executor.executeScript("window.document.getElementById('create_account').click()");
					
					
					// 3. Take a screenshot of the page
					//File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					//FileUtils.copyFile(file, new File("C://Screens/1.png"));
					
					System.out.println("End..");
		
		
		//Document doc = Jsoup.connect(url).get();
		//Elements elements=doc.select("a");
		//Elements buttons = doc.select("button");
		//System.out.println("Success");
	}

}
