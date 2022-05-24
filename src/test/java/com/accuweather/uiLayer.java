package com.accuweather;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.api.pageobject.CheckoutPageLocators;
import com.utility.TestBase;
import com.utility.apiUtlity;
import com.utility.customException;


public class uiLayer extends TestBase {
	public static  SoftAssert st;
	static WebDriver driver;
	
	@BeforeTest
	public void BeforeTest() {
		TestBase.init();
	}
	
	@Test(priority = 1, enabled = true, description = "Testcase to  get weather information")
	
	public void getinfo() throws Exception,customException {
		TestBase.init();
		st = new SoftAssert();
		String url = prop.getProperty("url");
		
		driver = TestBase.LaunchBrowser("chrome");
		driver.get(url);
		System.out.println("url opened");
		
		driver.findElement(By.xpath(CheckoutPageLocators.policyaccept)).click();
		System.out.println("terms and condition accepted");
		driver.findElement(By.xpath(CheckoutPageLocators.searchinput)).sendKeys("Delhi");
		WebElement dropdownele = driver.findElement(By.xpath(CheckoutPageLocators.inputdropdown));
		
		TestBase.VerifyElementDisplay(dropdownele);
		TestBase.VerifyElementEnable(dropdownele);
		dropdownele.click();
		String tempraturefromUI = driver.findElement(By.xpath(CheckoutPageLocators.temprature)).getText();
		
		Double TempraturefromAPI = apiUtlity.getinfoAPI();
		Double Convertedtempratureincelcius=TempraturefromAPI- 273.15;
		
		System.out.println(tempraturefromUI);
		System.out.println(Convertedtempratureincelcius); 
		
		
		       if(tempraturefromUI.equals(Convertedtempratureincelcius)){  
		  
		        
		        throw new customException("temprature of two sources have no variance");    
		    }  
		       else {   
		        System.out.println("temprature of two sources have variance");   
		        }   
		     }    
		
		
		
		
		
		
	}
	
