package com.adobe.main;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Starting...");
				
		PropertiesParams properties = PropertiesParams.get();
		properties.initialize();
		
		
		// Setting the Chrome driver
		System.setProperty(properties.getChromeDriver(), properties.getChromeDriverPath());
		WebDriver driver = new ChromeDriver();
		
		//Sign In Script
		Login login = new Login();
		login.presignIn(driver);
		login.signIn(driver);
		
		// Controller logic
		Controller controller = new Controller();
		controller.function(driver);
		
		System.out.println("end");
		
	}
}
