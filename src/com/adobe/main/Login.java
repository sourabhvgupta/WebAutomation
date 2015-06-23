package com.adobe.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	
	
	public void presignIn(WebDriver driver){
		// 1.Navigate to signIn url
		PropertiesParams properties = PropertiesParams.get();
		properties.initialize();
		String signinurl = properties.getsigninurl();
		driver.navigate().to(signinurl);
		driver.manage().window().maximize();
					
		// Pre-Sign In Page
		WebElement element = driver.findElement(By.name("commit"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);					
	}		
	
	public void signIn(WebDriver driver){
		
		try {
			
			PropertiesParams properties = PropertiesParams.get();
			properties.initialize();
			
			String username = properties.getUsername();
			String password = properties.getPassword();
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			/*// 1.Navigate to signIn url
			driver.navigate().to(signinurl);
			driver.manage().window().maximize();
			
			// Pre-Sign In Page
			WebElement element = driver.findElement(By.name("commit"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			*/
			
			// Sign-In Page
			driver.findElement(By.id("adobeid_username")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("adobeid_username")).sendKeys(username);
			Thread.sleep(2000);
			driver.findElement(By.name("password")).click();							
			executor.executeScript("document.getElementById('adobeid_password').setAttribute('value', '"+password+"');");
			Thread.sleep(5000);
			driver.findElement(By.id("sign_in")).click();
			//driver.findElement(By.id("adobeid_callback")).submit();
			
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
