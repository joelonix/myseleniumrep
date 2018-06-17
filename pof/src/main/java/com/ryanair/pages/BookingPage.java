package com.ryanair.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ryanair.base.TestBase;

/**
 * @author dmahadev
 *
 */
public class BookingPage extends TestBase{
	
	@FindBy(xpath="//div[(@class='flight-header__min-price hide-mobile')]//following-sibling::div[@class='core-btn-primary']")
	WebElement pricebutton;
	
	
	@FindBy(xpath="//span[@class='hide-mobile' and contains(text(),'Standard fare')]")
	WebElement standardfare;
	
	
	//If you want to check the failure scenario and the screenshot, please uncomment below lines and comment 
	//above two steps (xpath for standardfare)
//	@FindBy(xpath="//span[@class='mobile' and contains(text(),'Standard fare')]")
//	WebElement standardfare;
	
	@FindBy(xpath="//button[@class='core-btn-primary core-btn-block core-btn-medium']")
	WebElement continuebtn;
	
	public BookingPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnPriceButton(){
		this.pricebutton.click();
	}
	
	public void clickOnStandardFare(){
		this.standardfare.click();
		
	}
	
	public SeatSelectionPage clickOnContinue(){
		this.continuebtn.click();
		return new SeatSelectionPage();
	}

}
