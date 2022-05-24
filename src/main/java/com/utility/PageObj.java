package com.utility;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;


public class PageObj extends TestBase {
	
	
	
	public PageObj() {
		PageFactory.initElements(w, this);
	}

	public void initDriver() {
		PageFactory.initElements(w, this);
	}
	public void openBrowser_ops_console() {
		TestBase.init();
		String projectPath = System.getProperty("user.dir");
		String os = prop.getProperty("os");
		String url_ops = prop.getProperty("url_ops");
		if(os.equalsIgnoreCase("linux")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			try {
				System.out.println("Trying to get Token:");
				System.out.println("Driver Path:"+ projectPath + "/chromedriver");
				System.setProperty("webdriver.chrome.driver", projectPath + "/chromedriver");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				chromeOptions.addArguments("--window-size=1920,1080");
				chromeOptions.addArguments("--start-maximized");
				chromeOptions.addArguments("--headless");
				w = new ChromeDriver(chromeOptions);
				w.manage().window().maximize();
				w.get(url_ops);
			} catch (Exception e) {
				System.out.println("Exception occured while getting token:"+e);
				e.printStackTrace();
			}
		} else if (os.equalsIgnoreCase("window")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", projectPath + "\\chromedriver.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			chromeOptions.addArguments("--window-size=1366,768");
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--headless");
			w = new ChromeDriver(chromeOptions);
			w.manage().window().maximize();
			w.get(url_ops);
		}
	}
}
