package com.adobe.main;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		PropertiesParams properties = PropertiesParams.get();
		properties.initialize();
		System.out.println("Starting...");
		
		
		// Setting the Chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Adobe Work\\Automation\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new FirefoxDriver();
		Login login = new Login();
		login.presignIn(driver);
		login.signIn(driver);
		
		driver.navigate().to("https://relstage.typekit.com/fonts");
		Thread.sleep(5000);
		List<WebElement> elements = driver.findElements(By.className("info-bubble-icon"));
		for (WebElement webElement : elements) {
			if(webElement.isDisplayed()){
				driver.findElement(By.tagName("body")).click();
				webElement.click();
			}
			
			Thread.sleep(2000);
		}
		
		System.out.println("End");
		
	}

}
