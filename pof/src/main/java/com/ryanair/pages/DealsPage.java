package com.ryanair.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ryanair.base.TestBase;

/**
 * @author dmahadev
 *
 */
public class DealsPage extends TestBase{
	
	@FindBy(xpath="//button[@class='core-btn-primary core-btn-block core-btn-medium btn-text']")
	WebElement checkoutbtn;
	
	public DealsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public PassengerDetailsPage clickOnCheckOutButton(){
		wait.until(ExpectedConditions.elementToBeClickable(this.checkoutbtn));
		this.checkoutbtn.click();
		return new PassengerDetailsPage();
	}

}
