package com.salesforce.automation.scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salesforce.automation.basetest.BaseAutomation;
import com.salesforce.automation.pages.home.HomePage;
import com.salesforce.automation.pages.login.ForgotPasswordPage;
import com.salesforce.automation.pages.login.LoginPage;
import com.salesforce.automation.utilities.CommonUtilities;
import com.salesforce.automation.basetest.BaseAutomation;

@Listeners(com.salesforce.automation.scripts.ListenerClass.class)
public class TestScripts extends BaseAutomation {
	public static WebElement usernameHomePage;
	
	@Test (priority=1)
	public static void emptyPasswordTC1() throws Exception {
		
		String expected ="Please enter your"; // failing assertion by providing incorrect expected 
		System.out.println("Test Case 1 : Login Error ");
	
		LoginPage login = new LoginPage(driver);
		CommonUtilities cu = new CommonUtilities();
		Properties userPropertyFile=cu.loadFile("user");
		System.out.println("enetring property value");
		String usernamePath = getApplicationProperty("valid-username",userPropertyFile);
		System.out.println(usernamePath);
		String passwordPath = getApplicationProperty("empty-password",userPropertyFile);
		login.login(usernamePath, passwordPath);
		String actual = login.getErrorMessage();
		Assert.assertEquals(actual,expected);
		report.logTestInfo("TestScript1 complete");
		System.out.println("Test");

	}

@Test (priority=1)

	public static void loginSuccessTC2() throws Exception { //
		System.out.println("Test Case : Login To SalesForce - 2 ");
		String expected="Home Page ~ Salesforce - Developer Edition";
		LoginPage login = new LoginPage(driver);
		CommonUtilities cu = new CommonUtilities();
		Properties userPropertyFile=cu.loadFile("user");
		String usernamePath = getApplicationProperty("valid-username",userPropertyFile);
		String passwordPath = getApplicationProperty("valid-password",userPropertyFile);
		login.login(usernamePath, passwordPath);
		untilPageLoad();
		String actual=login.getPageTitle();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
		report.logTestInfo("TestScript2 complete");
		

	}

@Test(priority=3, dependsOnMethods={"loginSuccessTC2", "emptyPasswordTC1"})
	public static void logoutSuccessTC3() throws Exception { //
		System.out.println("Test Case 3 : Check RemeberMe");
		String expected="mamtarani@tekarch.com";
		LoginPage login = new LoginPage(driver);
		CommonUtilities cu = new CommonUtilities();
		Properties userPropertyFile=cu.loadFile("user");
		String usernamePath = getApplicationProperty("valid-username",userPropertyFile);
		String passwordPath = getApplicationProperty("valid-password",userPropertyFile);
		login.login(usernamePath, passwordPath);
		untilPageLoad();
		HomePage home=new HomePage(driver);
		home.home();
		untilPageLoad();
		String actual=home.getUserName();
		Assert.assertEquals(actual, expected);
		report.logTestInfo("TestScript3complete");
	}

@Test (priority=4)
	public static void forgotPasswordTC4A() throws Exception { //
		System.out.println("Test Case 4 : Forgot Password");
		String expected ="We’ve sent you an email with a link to finish resetting your password.";
		LoginPage login = new LoginPage(driver);
		login.clickForgotPasswordButton();
		CommonUtilities cu = new CommonUtilities();
		Properties userPropertyFile=cu.loadFile("user");
		String path = getApplicationProperty("valid-username", userPropertyFile);
		ForgotPasswordPage fgpwd = new ForgotPasswordPage(driver);
		fgpwd.forgotPassword(path);
		
		String actual = fgpwd.getConfirmationMessage();
		Assert.assertEquals(actual, expected);

	}
@Test(priority=5) 
	public static void invalidLoginTC4B() throws Exception { //
		System.out.println("Test Case 4B : ValidateLoginErrorMessage");
		String expected="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		LoginPage login = new LoginPage(driver);
		CommonUtilities cu = new CommonUtilities();
		Properties userPropertyFile=cu.loadFile("user");
		String usernamePath = getApplicationProperty("invalid-usernm",userPropertyFile);
		String passwordPath = getApplicationProperty("invalid-pw",userPropertyFile);
		login.login(usernamePath, passwordPath);
		untilPageLoad();
		String actual = login.getErrorMessage();
		Assert.assertEquals(actual, expected);
		System.out.println("Invalid details message displayed");
	}

}