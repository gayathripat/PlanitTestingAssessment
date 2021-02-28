package com.planittesting.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.planit.TestBase.TestBase;

public class TestCase_04 extends TestBase {
	
	@BeforeMethod
	public void setUp() throws Exception {
		
		launchBrowser(10);
	  }

	@Test()
	public  void executeTestCase4() throws Exception {
		
		
		//1.From the home page go to shop page
		clickM("xpath", "//a[contains(text(),'Shop')]");
		
		//2.Click buy button 2 times on “Funny Cow”
		clickM("xpath","//h4[contains(text(),'Funny Cow')]/following-sibling::p/a");
		clickM("xpath","//h4[contains(text(),'Funny Cow')]/following-sibling::p/a");
		
		//3.Click buy button 1 time on “Fluffy Bunny”
		clickM("xpath","//h4[contains(text(),'Fluffy Bunny')]/following-sibling::p/a");
		
		//4.Click the cart menu
		clickM("xpath","//a[contains(text(),'Cart')]");
		
		//5.Verify the items are in the cart
	
		boolean verifyItem1 = verifyTheItemsInTheCart(" Funny Cow","2");
		Assert.assertTrue(verifyItem1);
		System.out.println(verifyItem1);
		
		boolean verifyItem2 = verifyTheItemsInTheCart(" Fluffy Bunny","1");
		Assert.assertTrue(verifyItem2);
		System.out.println(verifyItem2);
		
	}
	
	@AfterMethod
	public void tearDown() 
	{
		closebrowser();
	}

	
	
}