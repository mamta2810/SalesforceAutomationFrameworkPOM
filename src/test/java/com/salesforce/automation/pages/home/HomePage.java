package com.salesforce.automation.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.automation.pages.base.BasePage;



public class HomePage extends BasePage{
	
	@FindBy(id="userNavButton") WebElement usernameHomePage;
	@FindBy(xpath="//a[@title=\"Logout\"]") WebElement logoutButton;
	@FindBy(id="idcard-identity") WebElement username;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void clickUserName() {
		try {
			clickElement(usernameHomePage, "User Name at Home Page");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void clickLogout() {
		try {
			clickElement(logoutButton, "Logout");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getUserName() {
		String text=readText(username,"User Name at Login Page");
		return text;
	} 
	
	
	public void home() throws Exception{
		clickUserName();
		clickLogout();
		
		
	}
	

}
