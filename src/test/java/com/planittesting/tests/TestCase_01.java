package com.planittesting.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.planit.TestBase.TestBase;

public class TestCase_01 extends TestBase {

	@BeforeMethod
	public void setUp() throws Exception {
		launchBrowser(10);
	}

	@Test()
	public void executeTestcase01() throws Exception {
		
		// 1.From the home page go to contact page
		clickM("xpath", "//a[contains(text(),'Contact')]");

		// 2.Click submit button
		clickM("xpath", "//a[contains(text(),'Submit')]");

		// 3.Validate errors
		boolean errorFornameText = validateErrorMesg("Forename is required", "id", "forename-err");
		Assert.assertTrue(errorFornameText, "Required error message is not dispalyed,expected =Forename is required");

		boolean errorEmailText = validateErrorMesg("Email is required", "id", "email-err");
		Assert.assertTrue(errorEmailText, "Required error message is not dispalyed,expected =Email is required");

		boolean errorMesgText = validateErrorMesg("Message is required", "id", "message-err");
		Assert.assertTrue(errorMesgText, "Required error message is not dispalyed,expected =Message is required");

		// 4.Populate mandatory fields
		enterText("id", "forename", "John");

		// 5.Validate errors are gone
		boolean errorFornameMesg = validateErrorMesgGone("Forename is required");
		Assert.assertFalse(errorFornameMesg);

		enterText("id", "email", "john.example@planit.com");

		boolean errorEmailMesg = validateErrorMesgGone("Email is required");
		Assert.assertFalse(errorEmailMesg);

		enterText("id", "message", "message is mandatory");

		boolean errorMesgFieldMesg = validateErrorMesgGone("Message is required");
		Assert.assertFalse(errorMesgFieldMesg);

	}

	@AfterMethod
	public void tearDown() {
		closebrowser();
	}

}
