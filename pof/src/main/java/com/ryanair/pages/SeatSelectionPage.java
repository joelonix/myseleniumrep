package com.ryanair.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ryanair.base.TestBase;

/**
 * @author dmahadev
 *
 */
public class SeatSelectionPage extends TestBase{
	
	@FindBy(xpath="//div[@class='seat-map-prompt-content']/button[@class='core-btn-primary same-seats']")
	WebElement okgotitbtn;
	
	@FindBy(xpath=".//div[@class='seat-map-rows']")
	WebElement seattable;
	
	@FindBy(xpath="//button[@class='core-btn-primary dialog-overlay-footer__ok-button']")
	WebElement reviewseatsbutton;
	
	@FindBy(xpath="//button[@class='core-btn-primary dialog-overlay-footer__ok-button']")
	WebElement confirmseatsbutton;
	
	@FindBy(xpath="//div[@class='priority-boarding-with-bags-popup__close-x']")
	WebElement closeofferdialog;
	
	public SeatSelectionPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnOkGotItbutton(){
		this.okgotitbtn.click();
	}
	
	public DealsPage clickOnReviewAndConfirmSeatsButton() throws InterruptedException{
		this.reviewseatsbutton.click();
		Thread.sleep(2000);
		this.confirmseatsbutton.click();
		Thread.sleep(2000);
		this.closeofferdialog.click();
		return new DealsPage();
		
	}
	
	
/**
 * Iterate through the seat selection table and select the required number of seats
 */
public void selectSeat(){
		
		
		List<WebElement> rows=this.seattable.findElements(By.xpath("//div[@class='seat-wrapper seat-group-ONSALE']"));
				
		outer:for(int rowcount=0;rowcount<rows.size();rowcount++){
			List<WebElement> cols=rows.get(rowcount).findElements(By.xpath("//span[@class='seat-row-seat onsale']"));
			
			for(int colcount=0;colcount<cols.size();colcount++){
				{
					cols.get(colcount).click();
					if(colcount==3) {
						break;
					}
				}
				
				
			}
			
			break outer;
		}

	}

}
