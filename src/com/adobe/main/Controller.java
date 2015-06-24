package com.adobe.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.internal.runners.model.EachTestNotifier;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Controller {

	public static final String BASEURL = "https://relstage.typekit.com";
	
	public void function(WebDriver driver) throws InterruptedException, IOException{
		
		PropertiesParams properties = PropertiesParams.get();
		properties.initialize();
		String locale = properties.getLocale();
		String filepath = properties.getFilepath();
		
		List<String> urlQueue = new ArrayList<String>();
		List<String> visitedURL = new ArrayList<String>();
		
		String currentURL = driver.getCurrentUrl();		
		urlQueue.add(currentURL);
		
		WebElement webElement = driver.findElement(By.name("locale"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		if(locale.equalsIgnoreCase("FRA")){
			executor.executeScript("arguments[0].click();", webElement);
		}else if(locale.equalsIgnoreCase("JPN")){
			executor.executeScript("arguments[1].click();", webElement);
		}
		
		
		int count = 0;
			
		while(!urlQueue.isEmpty()){
					
			// 1. Identify the URL from the queue
			String identifiedURL = urlQueue.remove(0);
			
			// 2. Navigate to identified URL
			driver.navigate().to(identifiedURL);
			driver.manage().window().maximize();
				
			if(driver.getCurrentUrl().contains("renga")){
				System.out.println("exist");
				Login login = new Login();
				login.signIn(driver);						
				
			}
			
			// 3. Take a screenshot of the page
			String filePath = filepath+locale+"/";
			StringBuilder filename = new StringBuilder(filePath);
			filename.append(locale+"_");
			filename.append(count);
			
			String year= Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
			String month = Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1);
			String day = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			
			filename.append("_"+year+"_"+month+"_"+day);
			filename.append(".png");
			Thread.sleep(10000);
			takeScreenshot(driver, filename.toString());
			
			// If page is scrollable
			int scrollTopCounter=1;
			JavascriptExecutor executer = (JavascriptExecutor)driver;
			Long windowHeight =(long)executer.executeScript("return window.innerHeight");
			Long pageHeight = (long)executer.executeScript("return document.body.scrollHeight");
			long scrollTop =0l;
			
			while(windowHeight+scrollTop <= pageHeight){
				executer.executeScript("window.scrollBy(0,500);", "");
				scrollTop = scrollTopCounter*500;
				scrollTopCounter++;
				Thread.sleep(2000);
				takeScreenshot(driver, filePath+locale+"_"+count+"_"+scrollTopCounter+"_"+year+"_"+month+"_"+day+".png");
			}
			
			// 4. Add current URL to visited List
			visitedURL.add(identifiedURL);
			
			 /*String filewritename= "MyFile.txt";
			  FileWriter fw = new FileWriter(filewritename,true); //the true will append the new data
			   fw.write(identifiedURL+", ");//appends the string to the file
			   fw.close();*/
			
			
			// 4. Identify all the anchor tags and buttons in the URL
			String docString = driver.getPageSource();
			Document doc = Jsoup.parse(docString);
			Elements elements=doc.select("a[href^=http:], a[href^=/]");
			
			for (Element element : elements) {
				String newURL = element.attr("href");
				String url = newURL;
				if(newURL.startsWith("/")){
					url = BASEURL+newURL;
				}
				
				boolean condition1 = !visitedURL.contains(url);
				boolean condition2 = !urlQueue.contains(url);
				boolean condition3 = !url.startsWith("https://relstage.typekit.com/fonts?");
				boolean condition4 = !url.startsWith("https://relstage.typekit.com/fonts/");
				boolean condition5 = !url.contains("blog");
				boolean condition6 = !url.contains("renga");
				
				boolean condition56 = url.contains("relstage");
				boolean condition7 = !url.startsWith("https://relstage.typekit.com/lists?");
				boolean condition8 = !url.startsWith("https://relstage.typekit.com/lists/");
				boolean condition9 = !url.startsWith("https://relstage.typekit.com/foundries/");
				boolean condition10 = !url.startsWith("https://relstage.typekit.com/gallery?");
				boolean condition11 = !url.startsWith("https://relstage.typekit.com/docs");
				
				
				if(condition1 && condition2 && condition3 && condition4 && condition56 && condition7 && condition8 && condition9 && condition10 && condition11){
					urlQueue.add(url);
				}
			}
											
			
			count++;
		}
	}
	
	public void takeScreenshot(WebDriver driver, String filename){
		try {
			
			File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);				
			FileUtils.copyFile(file, new File(filename));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
