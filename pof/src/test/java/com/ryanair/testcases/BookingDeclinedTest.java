package com.ryanair.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ryanair.base.TestBase;
import com.ryanair.pages.BookingPage;
import com.ryanair.pages.DealsPage;
import com.ryanair.pages.HomePage;
import com.ryanair.pages.LoginPage;
import com.ryanair.pages.PassengerDetailsPage;
import com.ryanair.pages.SeatSelectionPage;

/**
 * @author dmahadev
 *
 */
public class BookingDeclinedTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage = new HomePage();
	BookingPage bookingPage = new BookingPage();
	SeatSelectionPage seatSelectionPage = new SeatSelectionPage();
	DealsPage dealsPage = new DealsPage();
	PassengerDetailsPage passengerDetailsPage = new PassengerDetailsPage();
	
	public BookingDeclinedTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test
	public void bookingDeclinedTest() throws InterruptedException{
		try{
			
		//provide username and password to login
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String flyoutdate = prop.getProperty("flyoutdate");
		String flyoutmonth = prop.getProperty("flyoutmonth");
		String flyoutyear = prop.getProperty("flyoutyear");
		String adults = prop.getProperty("adults");
		String children = prop.getProperty("children");
		String teens = prop.getProperty("teens");
		String infants = prop.getProperty("infants");
		
		logger.info("RyanAir BookingDeclinedTest execution has started...");
		//loginpage will return object of type homepage
		homePage = loginPage.login(username, password); 
		
		boolean flag = homePage.validateLoggedInUser(); //Assert loggedin user
		Assert.assertTrue(flag);
		logger.info("Logged in Successfully and verified logged in user");
		
		Thread.sleep(3000);
		
		
		homePage.clickOnOneWay();
		logger.info("Clicked on One Way radio button");
		homePage.selectFromAirport();
		logger.info("Selected From Airport : " + prop.getProperty("fromairport"));
		homePage.selectToAirport();
		logger.info("Selected To Airport : " + prop.getProperty("toairport"));
		homePage.selectFlyOutDate(flyoutdate, flyoutmonth, flyoutyear);
		
		
		Thread.sleep(3000);
		homePage.selectPassengers(adults, children, teens, infants);
		logger.info("Number of passengers :" + "Adults: "+adults+"Children: "+children);
		Thread.sleep(3000);
		
		bookingPage = homePage.clickOnLetsgobutton();
		Thread.sleep(5000);
		
		logger.info("Searching for the best prices...");
		
		bookingPage.clickOnPriceButton();
		Thread.sleep(3000);
		logger.info("Best price selected...");
		
		bookingPage.clickOnStandardFare();
		Thread.sleep(3000);
		logger.info("Opted for 'Standard fare'");
		
		seatSelectionPage = bookingPage.clickOnContinue();
		Thread.sleep(3000);
		
		seatSelectionPage.clickOnOkGotItbutton();
		Thread.sleep(3000);
		
		seatSelectionPage.selectSeat();
		Thread.sleep(3000);
		
		dealsPage = seatSelectionPage.clickOnReviewAndConfirmSeatsButton();
		Thread.sleep(3000);
		logger.info("Seat Selection(s) are done...");
		
		passengerDetailsPage = dealsPage.clickOnCheckOutButton();
		Thread.sleep(3000);
		
		passengerDetailsPage.enterPassengerDetails();
		Thread.sleep(2000);
		logger.info("Passenger details are entered...");
		
		passengerDetailsPage.enterContactDetails();
		Thread.sleep(2000);
		logger.info("Contact details are entered... ");
		
		passengerDetailsPage.entercardandbillingdetails();
		Thread.sleep(2000);
		logger.info("Payment credit card and billing details are entered...");
		
		Assert.assertEquals(prop.getProperty("errormsg"), passengerDetailsPage.acceptTermsAndPay());
		logger.info("Assertion is done and error message is asserted");

		} catch(Throwable t){
			handleException(t);
		}
		
	}
	
	@AfterMethod
	public void teardown(){
		driver.quit();
		
	}

}
