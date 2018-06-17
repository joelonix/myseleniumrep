package com.ryanair.pages;

import com.ryanair.base.TestBase;
import com.ryanair.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author dmahadev
 *
 */
public class LoginPage extends TestBase{
	
	//Page Factory of Login Page - Object Repository[OR]
	
	@FindBy(id="myryanair-auth-login")
	WebElement login;
	
	@FindBy(name="emailAddress")
	WebElement username;
	
	@FindBy(xpath="//div[@class='form-field password']//input[starts-with(@id,'password')]")
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit' and @class='core-btn-primary']")
	WebElement loginbtn;
	
	@FindBy(xpath="//a[@class='ryanair-logo']")
	WebElement ryanairlogo;
	
	@FindBy(xpath="//div[@class='cookie-popup__close-btn']")
	WebElement cookieclosebtn;
	
	
	//Initializing the Page Objects
	public LoginPage(){		
		PageFactory.initElements(driver, this);
	}
	
	//Actions that can be performed on Login Page
	
	/**
	 * @return PageTitle
	 */
	public String validateLoginPageTitle(){
		return driver.getTitle();
		
	}
	
	/**
	 * @return RyanAir Logo
	 */
	public boolean validateRyanAirImage(){
		return ryanairlogo.isDisplayed();
		
	}
	
	
	/**
	 * @param usrname
	 * @param pwd
	 * @return HomePage instance
	 */
	public HomePage login(String usrname, String pwd){
		if(cookieclosebtn.isDisplayed())
			cookieclosebtn.click();
		
		this.login.click();
		this.username.sendKeys(usrname);
		this.password.sendKeys(pwd);
		this.loginbtn.click();
		
		return new HomePage();
		
	}

}
