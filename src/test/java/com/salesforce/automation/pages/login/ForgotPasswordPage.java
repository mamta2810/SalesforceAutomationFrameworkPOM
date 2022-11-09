package com.salesforce.automation.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.automation.pages.base.BasePage;

public class ForgotPasswordPage extends BasePage {
	@FindBy(id="un") WebElement usernameForgotPassword;
	@FindBy(id="continue") WebElement continueForgotPassword;
	@FindBy(xpath="//p[contains(text(),'We’ve sent you an email with a link to finish rese')]") WebElement confirmationMessageForEmail;
	
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterForgotPasswordUserName(String usname) throws Exception {
		visibleWait(usernameForgotPassword);
		enterElement(usernameForgotPassword,usname,"User Name");
	}
	
	public void clickContinueForgotPassword() throws Exception{
		clickElement(continueForgotPassword, "Continue Forgot Password");
	}
	
	public String getConfirmationMessage() {
		String text=readText(confirmationMessageForEmail,"Confirmation Message After email sent");
		return text;
	} 
	
	public void forgotPassword(String usname) throws Exception{
		
		enterForgotPasswordUserName(usname);
		clickContinueForgotPassword();
		
	}

}
