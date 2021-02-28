package com.planittesting.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.planit.TestBase.TestBase;

public class TestCase_02 extends TestBase {

	@BeforeMethod
	public void setUp() throws Exception {
		launchBrowser(10);
	}

	@Test
	public void executeTestcase2() throws Exception {

		//1.From the home page go to contact page
		
		clickM("xpath", "//a[contains(text(),'Contact')]");
		
		//2.Populate mandatory fields
		enterText("id", "forename", "John");
		enterText("id", "email", "john.example@planit.com");
		enterText("id", "message", "message is mandatory");
		
		
		//3.Click submit button
		clickM("xpath", "//a[contains(text(),'Submit')]");

		WebElement mesg = getElement("xpath", "//div[@class='alert alert-success']");
		explicitWait("visibility", driver, mesg, 30);
		
		//4.Validate successful submission message
		boolean successMesg = validateSucesfulSubmissionMesg("Thanks John, we appreciate your feedback.", "xpath",
				"//div[@class='alert alert-success']");
		Assert.assertTrue(successMesg,"Required successful submisssion message is not dispalyed,expected =Thanks John, we appreciate your feedback.");

	}

	@AfterMethod
	public void tearDown() {
		closebrowser();
	}

}
