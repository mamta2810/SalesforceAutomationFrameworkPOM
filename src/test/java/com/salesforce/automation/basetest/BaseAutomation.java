package com.salesforce.automation.basetest;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.time.*;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salesforce.automation.utilities.CommonUtilities;
import com.salesforce.automation.utilities.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseAutomation extends CommonUtilities {
	public static WebDriver driver = null;
	public static String path;
	public static WebElement username;
	public static WebElement password;
	public static WebElement loginButton;
	public static WebElement forgotPassword;
	public static WebElement rememberMe;
	public static GenerateReports report = null;

	 @BeforeTest
	public static void setupBeforeTest() {
		report = GenerateReports.getInstance();
		GenerateReports.startExtentReport();
		System.out.println("before test is executing");
	}

	@Parameters({ "browser" })
	@BeforeMethod
	public static void beforeMethod1(String browser,Method m) throws Exception {
		System.out.println("before method is executing");
		browserSel(browser);
		GenerateReports.StartSingleTestReport(m.getName());
		goToURL();
		untilPageLoad();
		
	}

	@AfterMethod
	public static void afterMethod() {
		System.out.println("after method is executing");
		report.logTestInfo("after method execution is started");
		try {
			closeDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public static void afterTest() {
		System.out.println("After test is executing");
		report.endReport();

	}
	
@Test

	public static void browserSel(String browser) throws Exception {

		switch (browser) {

		case "chrome":
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("None of the chrome, edge or firefox browser selected");
		}
	}

	

	public static void goToURL() throws InterruptedException {
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
	}
	
	public static void untilPageLoad() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
	}
	
	public static void closeDriver() throws Exception {
		Thread.sleep(3000);
		driver.close();
	}

	

	public static void getWebElements() {
		username = driver.findElement(By.id("username"));
		password = driver.findElement(By.id("password"));
		loginButton = driver.findElement(By.xpath("//input[@id=\"Login\"]"));
		forgotPassword = driver.findElement(By.xpath("//*[@id=\"forgot_password_link\"]"));
		rememberMe = driver.findElement(By.xpath("//*[@id=\"rememberUn\"]"));

	}

	
}
