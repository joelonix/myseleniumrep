package com.ryanair.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ryanair.base.TestBase;
import com.ryanair.pages.HomePage;
import com.ryanair.pages.LoginPage;

/**
 * @author dmahadev
 *
 */
public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest(){
		//call super class constructor to call TestBase class constructor so that properties 
		//are initialized before calling initialization method
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();
	}
	
	
	//@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Official Ryanair website | Cheap flights from Ireland | Ryanair");
	}
	
	
	//@Test(priority=2)
	public void loginPageLogoTest(){
		boolean logoexists = loginPage.validateRyanAirImage();
		Assert.assertTrue(logoexists);
		
	}
	
	@Test(priority=3)
	public void loginTest(){
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		homePage = loginPage.login(username, password);
		boolean flag = homePage.validateLoggedInUser();
		Assert.assertTrue(flag);
	}

	
	@AfterMethod
	public void teardown(){
		driver.quit();
		
	}
}
