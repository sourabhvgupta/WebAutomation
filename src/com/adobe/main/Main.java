package com.adobe.main;

import java.io.IOException;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Starting...");
				
		
		PropertiesParams properties = PropertiesParams.get();
		properties.initialize();
			
		String locale = properties.getLocale();
		String filepath = properties.getFilepath();
		
		String year= Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		String month = Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1);
		String day = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		
		// Setting the Chrome driver
		//System.setProperty(properties.getChromeDriver(), properties.getChromeDriverPath());
		WebDriver driver = new FirefoxDriver();
		Controller controller = new Controller();
		
		driver.navigate().to("http://relstage.typekit.com/home?locale=de_DE");
		controller.takeScreenshot(driver, filepath+locale+"_home_"+year+"_"+month+"_"+day+".png");
		
		//Sign In Script
		Login login = new Login();
		login.presignIn(driver);
		login.signIn(driver);
		
		// Controller logic
		
		controller.function(driver);
		
		System.out.println("end");
		
	}
}
