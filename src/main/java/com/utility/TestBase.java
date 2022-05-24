package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TestBase {
	public static Properties prop;
	public static String projectPath = System.getProperty("user.dir");
	public static WebDriver w;
	public static String driverPath = "/chromedriver";
	public static String driverPathExe = "/chromedriver.exe";
	public static RequestSpecification httpRequest;
	public static Response response;

	public static void init() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/OR.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updatePropertiesFile(String key, String value) {
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/OR.properties");
			Properties props = new Properties();
			props.load(fis);
			fis.close();
			System.out.println("path is :" + System.getProperty("user.dir") + File.separator + "OR.properties");
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.dir") + File.separator + "OR.properties");
			props.setProperty(key, value);
			props.store(fos, null);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver LaunchBrowser(String BrowserName) {
		if (BrowserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
			w = new ChromeDriver();
			w.manage().deleteAllCookies();
			w.manage().window().maximize();
			w.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			System.out.println("Chrome Launched");
		}
		if (BrowserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
			w = new FirefoxDriver();
			w.manage().deleteAllCookies();
			w.manage().window().maximize();
			w.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			System.out.println("Firefox Launched");
		}
		if (BrowserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "./IEDriverServer.exe");
			w = new InternetExplorerDriver();
			w.manage().deleteAllCookies();
			w.manage().window().maximize();
			w.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			System.out.println("IE Launched");
		}
		return w;

	}
	
	
	public static void VerifyElementDisplay(WebElement element) {
		boolean flag = element.isDisplayed();
		if (flag) {
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element is not displayed");
		}
	}

	public static void VerifyElementselected(WebElement element) {
		boolean flag = element.isSelected();
		if (flag) {
			System.out.println("Element is Selected");
		} else {
			element.click();
		}
	}

	public static void VerifyElementEnable(WebElement element) {
		boolean flag = element.isEnabled();
		if (flag) {
			System.out.println("Element/button is enabled");
		} else {
			System.out.println("Element/button is not enabled");
		}
	}

	
	
	public static void staticWait(int sec) {
		long timeSec = sec * 1000;
		try {
			Thread.sleep(timeSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void tearDown() {
		try {
			w.close();
			System.out.println("Closing chrome browser");
			w.quit();
			System.out.println("Quit chrome driver");
		} catch (UnhandledAlertException e) {
			w.quit();
			System.out.println("Quit chrome driver");
		}
	}
	


}
