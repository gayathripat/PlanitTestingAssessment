package com.planit.TestBase;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;

	public static WebDriver launchBrowser(int time) throws Exception {
		prop = new Properties();
		FileInputStream fip = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\planit\\TestBase\\config.properties");
		prop.load(fip);
		String browserType = prop.getProperty("browserType");
		switch (browserType) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
		case "IE":
			WebDriverManager.chromedriver().setup();
			driver = new InternetExplorerDriver();
		default:
			throw new Exception("invalid browserTypes:valid browsers are chrome,firefox,IE");
		}

		String appURL = getApplicationURL();
		driver.get(appURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		return driver;

	}

	public static String getApplicationURL() throws Exception {
		String envType = prop.getProperty("envType");
		switch (envType) {
		case "DEV":
			return prop.getProperty("Devurl");
		case "TEST":
			return prop.getProperty("TestUrl");
		case "STAGE":
			return prop.getProperty("StageUrl");
		case "PROD":
			return prop.getProperty("ProdUrl");
		default:
			throw new Exception("invalid envType:valid envtypes are dev,test,stage,prod");
		}
	}

	public static WebElement getElement(String locatorType, String locatorVal) throws Exception

	{
		WebElement element;
		List<WebElement> lst = getElements(locatorType, locatorVal);
		return element = lst.get(0);
	}

	public static void clear(String locType, String locvalue) throws Exception {
		getElement(locType, locvalue).clear();

	}

	public static List<WebElement> getElements(String locatorType, String locatorVal) throws Exception {
		return driver.findElements(getByObject(locatorType, locatorVal));

	}

	public static By getByObject(String locatorType, String locatorVal) throws Exception {
		By by = null;
		switch (locatorType) {
		case "xpath":
			by = By.xpath(locatorVal);
			break;
		case "id":
			by = By.id(locatorVal);
			break;
		case "name":
			by = By.name(locatorVal);
			break;
		case "className":
			by = By.id(locatorVal);
			break;
		case "linkText":
			by = By.linkText(locatorVal);
			break;
		case "PartialLinkText":
			by = By.partialLinkText(locatorVal);
			break;
		case "tagName":
			by = By.tagName(locatorVal);
			break;
		case "cssSelector":
			by = By.cssSelector(locatorVal);
			break;
		default:
			throw new Exception("invalid drpdwntype:valid are text,value,index");
		}
		return by;

	}

	public static void enterText(String locType, String locvalue, String value) throws Exception {

		getElement(locType, locvalue).sendKeys(value);
	}

	public static void clickM(String locType, String locvalue) throws Exception {

		getElement(locType, locvalue).click();
	}

	public static boolean verifyPageTitle(String expTitle) {

		String actualTitle = driver.getTitle();
		if (expTitle.equals(actualTitle)) {// we can use contains too
			return true;
		} else {
			return false;
		}

	}

	public boolean validateErrorMesg(String expErrorTitle, String locType, String locVal) throws Exception {
		String actualErrorTitle = getElementText(locType, locVal);
		if (expErrorTitle.equals(actualErrorTitle.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateErrorMesgGone(String expErrorMesg) {
		String pagecontent = driver.getPageSource();
		if (pagecontent.equals(expErrorMesg)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validateSucesfulSubmissionMesg(String expSucesfulSubmissionMesg, String locType, String locVal)
			throws Exception {
		String actSucesfulSubmissionMesg = getElementText(locType, locVal);
		if (expSucesfulSubmissionMesg.equals(expSucesfulSubmissionMesg.trim())) {
			return true;
		} else {
			return false;
		}

	}

	public static String getElementText(String locType, String locVal) throws Exception {
		String elementtext = getElement(locType, locVal).getText();
		return elementtext;
	}

	public static String getAttributeValue(String locType, String locVal) throws Exception
	{
		String elementValue = getElement(locType, locVal).getAttribute("value");
		return elementValue;
		
	}
	public static boolean visibilityConditions(String visibilityType, String locType, String locVal) throws Exception {
		switch (visibilityType) {
		case "enable":
			boolean enable = getElement(locType, locVal).isEnabled();
			return enable;
		case "select":
			boolean slct = getElement(locType, locVal).isSelected();
			return slct;
		case "disable":
			boolean display = getElement(locType, locVal).isDisplayed();
			return display;

		}
		return false;

	}

	public static void explicitWait(String explicitType, WebDriver driver, WebElement locator, int timeout)
			throws Exception {
		switch (explicitType) {
		case "click":
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
			locator.click();
			break;
		case "select":
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeSelected(locator));
			break;
		case "alert":
			new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
			break;
		case "visibility":
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(locator));
			break;
		default:
			throw new Exception("Invalid explicitwaits:valid are clickable,alert,visibility,selected");

		}
	}


	public boolean verifyTheItemsInTheCart(String productName, String ItemCount) throws Exception {

		String actCount = getAttributeValue("xpath",
				"//td[contains(text(),'"+productName+"')]/following-sibling::td/input");
		
		if (actCount.contains(ItemCount)) {
			return true;

		} else {
			return false;
		}
	}
	

	public static void closebrowser() {
		driver.close();
	}

}
