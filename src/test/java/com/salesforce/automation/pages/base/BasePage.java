package com.salesforce.automation.pages.base;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.salesforce.automation.utilities.GenerateReports;

public class BasePage {
	protected static WebDriverWait wait;
	protected static WebDriver driver;
	//protected static GenerateReports report;
	public static String path;

	protected BasePage(WebDriver driver) {
		BasePage.driver = driver;
		PageFactory.initElements(driver, this);
		//report=GenerateReports.getInstance();
	}

	public static void clearElementText(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			//report.logTestInfo(objName + " field text cleared successfully");
			// System.out.println(objName + " field text cleared successfully");
		} else
			//report.logTestFail(objName + " not displayed");
		System.out.println(objName + " not displayed");
	}

	public static void enterElement(WebElement element, String text, String objName) throws Exception {
		path = text;
		try {
			visibleWait(element);
			clearElementText(element, objName);

			element.sendKeys(path);
		//	report.logTestInfo("text entered in " + objName + " field successfully");
			// System.out.println("text entered in " + objName + " field successfully");
		} catch (Exception e) {
			//report.logTestFail(objName + " not displayed");
			 System.out.println(objName + " not displayed");
		}

	}

	public static void clickElement(WebElement element, String objName) throws Exception {
		if (element.isDisplayed()) {
			clickableWait(element);
			element.click();
			//report.logTestInfo(objName + " pressed scuccesfully.");
			System.out.println(objName + " pressed scuccesfully.");
		} else
		//	report.logTestFail(objName + " not displayed");
		 System.out.println(objName + " not displayed");

	}

	public static void moveToDropDown(WebElement element, String objName) throws AWTException, Exception {
		Thread.sleep(2000);

		Actions ac = new Actions(driver);
		ac.moveToElement(element).build().perform();

		/*
		 * Robot robo = new Robot(); robo.keyPress(KeyEvent.VK_DOWN);
		 * robo.keyRelease(KeyEvent.VK_DOWN); robo.keyPress(KeyEvent.VK_ENTER);
		 * robo.keyRelease(KeyEvent.VK_ENTER);
		 */

	}

	public static void visibleWait(WebElement element) {
		 wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void clickableWait(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void handlePopup(WebElement element1, WebElement element2, String objName) throws Exception {

		if (element1.isDisplayed()) {
			clickElement(element2, objName);

		} else
		//	report.logTestFail("Pop up on account click isn't displayed");
		 System.out.println("Pop up on account click isn't displayed");
	}

	public static void selectElement(WebElement element, String text, String objName) {
		Select sc = new Select(element);
		if (element.isEnabled()) {
			sc.selectByVisibleText(text);
		}

		else
		//	report.logTestFail(objName + " is not enabled");
		 System.out.println(objName + " is not enabled");

	}

	public static String takeScreenshot(String objName) {
		// it will take screenshot of single element
		//File srcFile = element.getScreenshotAs(OutputType.FILE);

		// this is to take full screen screenshot, method available in firefox only
	File src1File = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm_ss");
		String dateTime = dateFormat.format(date);
		File dstFile = new File(System.getProperty("user.dir") + "/Screenshots/" + dateTime + ".png");
		
		String ssLocation=System.getProperty("user.dir") + "/Screenshots/" + dateTime + ".png";
		System.out.println("file is stored at " + ssLocation);
		try {
			// FileHandler.copy(scrFile, dstFile);
			FileHandler.copy(src1File, dstFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	report.logTestFail("problem while takign screenshot");
			 System.out.println("problem while takign screenshot");
		}
		
		return ssLocation;
	}
	
	public static String readText(WebElement element, String objName) {
		visibleWait(element);
		String text=element.getText();
		return text;
		
	}
	
	public String getPageTitle() {
	String text=driver.getTitle();
		return text;
	}
	
	public static void refreshPage() {
		driver.navigate().refresh();
		//report.logTestInfo("page got refreshed");
	} 

	

}
