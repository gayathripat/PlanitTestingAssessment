package com.planittesting.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.planit.TestBase.TestBase;

public class TestCase_03 extends  TestBase
{
	@BeforeMethod
	public void setUp() throws Exception {
		launchBrowser(10);
	}

	@Test
	public  void executeTestCase3() throws Exception {
		
		//1.From the home page go to contact page
		clickM("xpath", "//a[contains(text(),'Contact')]");
		
		//2.Populate mandatory fields with invalid data
		//I have tried to use invalid data (using special characters and numerics 
		//but Forename Field and Message Filed is not throwing any error message with such data
		/*enterText("id", "forename", "#$#$#$ ");
		
		boolean validFornameText = validateErrorMesg("Please enter a valid forename", "id", "forename-err");
		Assert.assertTrue(validFornameText);
		//System.out.println(ErrorFornameText);
		*/
		enterText("id", "email", "asd");
		
		boolean validEmailText = validateErrorMesg("Please enter a valid email", "id", "email-err");
		Assert.assertTrue(validEmailText);
		
		
		enterText("id", "telephone", "aasdf");
		
		boolean validTelephoneText = validateErrorMesg("Please enter a valid telephone number", "id", "telephone-err");
		Assert.assertTrue(validTelephoneText);
		
		/*enterText("id", "message", "@@@@@");
		boolean validmesgText = validateDisplayMesg("Please enter a valid email", "id", "message-err");
		Assert.assertTrue(validmesgText);*/

	}

	@AfterMethod
	public void tearDown() {
		closebrowser();
	}

}
