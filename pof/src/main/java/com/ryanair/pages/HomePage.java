package com.ryanair.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ryanair.base.TestBase;

/**
 * @author dmahadev
 *
 */
public class HomePage extends TestBase{
	
	@FindBy(xpath="//span[contains(text(),'leo')]")
	WebElement loggedinuser;
	
	@FindBy(id="flight-search-type-option-one-way")
	WebElement onewayradiobutton;
	
	@FindBy(xpath="//input[@type='radio' and @value='return']")
	WebElement returnradiobutton;
	
//	@FindBy(xpath="//span[@id='tab-flights-text']")
//	WebElement flightstab;
	
	@FindBy(xpath="//span[text()='Flights']")
	WebElement flightstab;
	
	@FindBy(xpath="//input[@type='text' and @placeholder='Departure airport']")
	WebElement departureairportname;
	
	@FindBy(xpath="//input[@type='text' and @placeholder='Destination airport']")
	WebElement destinationairportname;
	
	@FindBy(xpath="//button[@class='core-btn-primary core-btn-block core-btn-big']")
	WebElement continuebtn;
	
	@FindBy(xpath="//span[text()='Berlin Schönefeld']")
	WebElement selectdestinationairport;
	
	@FindBy(xpath="//input[@type='text' and @aria-label='Fly out: - DD']")
	WebElement flyoutdate;
	
	@FindBy(xpath="//input[@type='text' and @aria-label='Fly out: - MM']")
	WebElement flyoutmonth;
	
	@FindBy(xpath="//input[@type='text' and @aria-label='Fly out: - YYYY']")
	WebElement flyoutyear;
	
	@FindBy(xpath="//input[@type='text' and @aria-label='Fly back: - DD']")
	WebElement flybackdate;
	
	@FindBy(xpath="//input[@type='text' and @aria-label='Fly back: - MM']")
	WebElement flybackmonth;
	
	@FindBy(xpath="//input[@type='text' and @aria-label='Fly back: - YYYY']")
	WebElement flybackyear;
	
	@FindBy(xpath="//div[@class='col-passengers']")
	WebElement passengersdropdown;
	
	@FindBy(xpath="//div[@class='label' and contains(text(), 'Adults')]//parent::div[@class='details-label']//following-sibling::div[@class='details-controls']//button[@class='core-btn inc core-btn-wrap']")
	WebElement addadultpassengersbutton;
	
	@FindBy(xpath="//div[@class='label' and contains(text(), 'Children')]//parent::div[@class='details-label']//following-sibling::div[@class='details-controls']//button[@class='core-btn inc core-btn-wrap']")
	WebElement addchildrenpassengersbutton;
	
	@FindBy(xpath="//div[@class='label' and contains(text(), 'Teens')]//parent::div[@class='details-label']//following-sibling::div[@class='details-controls']//button[@class='core-btn inc core-btn-wrap']")
	WebElement addteenpassengersbutton;
	
	@FindBy(xpath="//div[@class='label' and contains(text(), 'Infants')]//parent::div[@class='details-label']//following-sibling::div[@class='details-controls']//button[@class='core-btn inc core-btn-wrap']")
	WebElement addinfantspassengersbutton;
	
	@FindBy(xpath="//span[@translate='common.buttons.lets_go']//parent::button")
	WebElement letsgobutton;
	
	//Initialize the page objects	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateLoggedInUser(){
		return loggedinuser.isDisplayed();
		
	}
	
	
	public void clickOnFlightstab(){
		this.flightstab.click();
		
	}
	
	public void clickOnOneWay(){
		this.onewayradiobutton.click();
	}
	
	public void clickOnReturn(){
		this.returnradiobutton.click();
	}
	
	public void selectFromAirport(){
		this.departureairportname.clear();
		this.departureairportname.sendKeys(prop.getProperty("fromairport"));
	}
	
	public void selectToAirport() throws InterruptedException{
		this.destinationairportname.clear();
		this.destinationairportname.sendKeys(prop.getProperty("toairport"));
		this.continuebtn.click();
		Thread.sleep(2000);
		//this.selectdestinationairport.click();
		
	}
	
	/**
	 * @param date
	 * @param month
	 * @param year
	 */
	public void selectFlyOutDate(String date, String month, String year){
		this.flyoutdate.sendKeys(date);
		this.flyoutmonth.sendKeys(month);
		this.flyoutyear.sendKeys(year);
		
	}
	
	public void selectFlyBackDate(){
		this.flybackdate.sendKeys(prop.getProperty("flybackdate"));
		this.flybackmonth.sendKeys(prop.getProperty("flybackmonth"));
		this.flybackyear.sendKeys(prop.getProperty("flybackyear"));
		
	}
	
	/**
	 * @param adults
	 * @param children
	 * @param teens
	 * @param infants
	 */
	public void selectPassengers(String adults, String children, String teens, String infants){
		this.passengersdropdown.click();
		//Start with 1 as already 1 adult passenger will be added by default
		for(int i=1;i<Integer.parseInt(adults); i++){
			this.addadultpassengersbutton.click();
		}
		
		for(int j=0;j<Integer.parseInt(children); j++){
			this.addchildrenpassengersbutton.click();
		}
		
		for(int k=0;k<Integer.parseInt(teens); k++){
			this.addteenpassengersbutton.click();
		}
		
		for(int l=0;l<Integer.parseInt(infants); l++){
			this.addinfantspassengersbutton.click();
		}
	}
	
	/**
	 * @return BookingPage Instance
	 */
	public BookingPage clickOnLetsgobutton(){
		this.letsgobutton.click();
		return new BookingPage();
	}
	

}
