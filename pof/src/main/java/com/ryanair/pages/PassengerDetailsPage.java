package com.ryanair.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ryanair.base.TestBase;

/**
 * @author dmahadev
 *
 */
public class PassengerDetailsPage extends TestBase{
	
	@FindBy(xpath="//passengers-form//following-sibling::passenger-input-group[last()-2]//select[starts-with(@id,'title')]")
	WebElement passenger1Title;
	
	@FindBy(xpath="//passengers-form//following-sibling::passenger-input-group[last()-2]//input[starts-with(@id,'firstName')]")
	WebElement passenger1FN;
	
	@FindBy(xpath="//passengers-form//following-sibling::passenger-input-group[last()-2]//input[starts-with(@id,'lastName')]")
	WebElement passenger1LN;
	
	@FindBy(xpath="//passengers-form//following-sibling::passenger-input-group[last()-1]//select[starts-with(@id,'title')]")
	WebElement passenger2Title;
	
	@FindBy(xpath="//passengers-form//following-sibling::passenger-input-group[last()-1]//input[starts-with(@id,'firstName')]")
	WebElement passenger2FN;
	
	@FindBy(xpath="//passengers-form//following-sibling::passenger-input-group[last()-1]//input[starts-with(@id,'lastName')]")
	WebElement passenger2LN;
	
	@FindBy(xpath="//passengers-form//following-sibling::passenger-input-group[last()]//input[starts-with(@id,'firstName')]")
	WebElement passenger3FN;
	
	@FindBy(xpath="//passengers-form//following-sibling::passenger-input-group[last()]//input[starts-with(@id,'lastName')]")
	WebElement passenger3LN;
	
	@FindBy(xpath="//select[@name='phoneNumberCountry']")
	WebElement phonenumbercountrydropdown;
	
	@FindBy(xpath="//input[@name='phoneNumber']")
	WebElement phonenumber;
	
	@FindBy(xpath="//input[@name='cardNumber']")
	WebElement cardnumber;
	
	@FindBy(xpath="//select[@name='cardType']")
	WebElement cardtypedropdown;
	
	@FindBy(xpath="//select[@name='expiryMonth']")
	WebElement expirymonthdropdown;
	
	@FindBy(xpath="//select[@name='expiryYear']")
	WebElement expiryyeardropdown;
	
	@FindBy(xpath="//input[@name='securityCode']")
	WebElement securitycode;
	
	@FindBy(xpath="//input[@name='cardHolderName']")
	WebElement cardholdername;
	
	@FindBy(xpath="//input[@name='billingAddressAddressLine1']")
	WebElement billingaddress;
	
	@FindBy(xpath="//input[@name='billingAddressCity']")
	WebElement billingaddresscity;
	
	@FindBy(xpath="//input[@name='billingAddressPostcode']")
	WebElement billingpostcode;
	
	@FindBy(xpath="//select[@name='billingAddressCountry']")
	WebElement billingcountry;
	
	@FindBy(xpath="//div[@class='terms']")
	WebElement termscheckbox;
	
	@FindBy(xpath="//button[text()='Pay Now']")
	WebElement paynowbtn;
	
	@FindBy(xpath="//div[text()='Oh. There was a problem']")
	WebElement errormsg;
	
	
	
	public PassengerDetailsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void enterPassengerDetails() throws InterruptedException{
		
		//Add Passenger1 details
		Thread.sleep(2000);
		Select title1 = new Select(this.passenger1Title);
		title1.selectByVisibleText(prop.getProperty("title1"));
		this.passenger1FN.sendKeys(prop.getProperty("passenger1FN"));
		this.passenger1LN.sendKeys(prop.getProperty("passenger1LN"));
		Thread.sleep(2000);
		
		//Add Passenger2 details
		Select title2 = new Select(this.passenger2Title);
		title2.selectByVisibleText(prop.getProperty("title2"));
		this.passenger2FN.sendKeys(prop.getProperty("passenger2FN"));
		this.passenger2LN.sendKeys(prop.getProperty("passenger2LN"));
		
		//Add passenger3 details
		Thread.sleep(2000);
		this.passenger3FN.sendKeys(prop.getProperty("passenger3FN"));
		this.passenger3LN.sendKeys(prop.getProperty("passenger3LN"));
		
		
	}
	
	
	public void enterContactDetails() throws InterruptedException{
		Thread.sleep(2000);
		
		Select country = new Select(this.phonenumbercountrydropdown);
		country.selectByVisibleText(prop.getProperty("country"));
		
		this.phonenumber.sendKeys(prop.getProperty("phonenumber"));
		
	}
	
	public void entercardandbillingdetails() throws InterruptedException{
		Thread.sleep(2000);
		this.cardnumber.sendKeys(prop.getProperty("cardnumber"));
		
		Select cardtype = new Select(this.cardtypedropdown);
		cardtype.selectByVisibleText(prop.getProperty("cardtype"));
		
		Select expirymonth = new Select(this.expirymonthdropdown);
		expirymonth.selectByVisibleText(prop.getProperty("expirymonth"));
		
		Select expiryyear = new Select(this.expiryyeardropdown);
		expiryyear.selectByVisibleText(prop.getProperty("expiryyear"));
		
		this.securitycode.sendKeys(prop.getProperty("securitycode"));
		
		
		this.cardholdername.sendKeys(prop.getProperty("cardholdername"));
		this.billingaddress.sendKeys(prop.getProperty("address1"));
		this.billingaddresscity.sendKeys(prop.getProperty("city"));
		this.billingpostcode.sendKeys(prop.getProperty("postcode"));
		
		Select billingcouontry = new Select(this.billingcountry);
		billingcouontry.selectByVisibleText(prop.getProperty("country"));
		
		
	}
	
	public String acceptTermsAndPay() throws InterruptedException{
		Thread.sleep(2000);
		this.termscheckbox.click();
		
		Thread.sleep(2000);
		this.paynowbtn.click();
		
		String msg = this.errormsg.getText();
		return msg;
		
		
	}

}
