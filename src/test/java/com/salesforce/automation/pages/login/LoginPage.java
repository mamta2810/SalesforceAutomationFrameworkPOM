package com.salesforce.automation.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.automation.pages.base.BasePage;



public class LoginPage extends BasePage{
	
	
	@FindBy(id="username") WebElement username;
	@FindBy(id="password") WebElement password;
	@FindBy(id="Login") WebElement loginButton;
	@FindBy(id="error") WebElement errorMessage;
	@FindBy(id="rememberUn") WebElement rememberMe;
	@FindBy(id="forgot_password_link") WebElement forgotPassword;
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String usname) throws Exception {
		visibleWait(username);
		enterElement(username,usname,"User Name");
	}
	
	public void enterPassword(String pwd) throws Exception{
		enterElement(password,pwd,"Password");
	}
	
	public void clickLoginButton() throws Exception{
		clickElement(loginButton, "Login Button");
	}
	
	public void clickForgotPasswordButton() throws Exception{
		clickElement(forgotPassword, "Forgot Password");
	}
	public String getErrorMessage() {
		String text=readText(errorMessage,"Error message of field");
		return text;
	} 
	
	public void clickRememberMe() throws Exception{
		clickElement(rememberMe, "Remember Me");
	}
	
	
	public void login(String usname, String pwd ) throws Exception{
		enterUserName(usname);
		enterPassword(pwd);
		clickRememberMe();
		clickLoginButton();
		
		
		
	}
	
}
