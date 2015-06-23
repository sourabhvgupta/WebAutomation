package com.adobe.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParams {

	String chromeDriver;
	String chromeDriverPath;
	String signinurl;
	String username;
	String password;
	String locale;
	String filepath;
	
	
	private static PropertiesParams propertiesparams = new PropertiesParams();
	public static PropertiesParams get(){
		return propertiesparams;
	}
	public void initialize(){
		try {
			
			File file = new File("src/com/adobe/main/config.properties");
			FileInputStream fileInput;
			fileInput = new FileInputStream(file);
		
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			
			setChromeDriver(properties.getProperty("chromedriver"));
			setChromeDriverPath(properties.getProperty("chromedriverpath"));
			setsigninurl(properties.getProperty("signinurl"));
			setUsername(properties.getProperty("username"));
			setPassword(properties.getProperty("password"));
			setLocale(properties.getProperty("locale"));
			setFilepath(properties.getProperty("filepath"));
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public String getChromeDriver() {
		return chromeDriver;
	}
	public void setChromeDriver(String chromeDriver) {
		this.chromeDriver = chromeDriver;
	}
	public String getChromeDriverPath() {
		return chromeDriverPath;
	}
	public void setChromeDriverPath(String chromeDriverPath) {
		this.chromeDriverPath = chromeDriverPath;
	}
	public String getsigninurl() {
		return signinurl;
	}
	public void setsigninurl(String signinurl) {
		this.signinurl = signinurl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
}
