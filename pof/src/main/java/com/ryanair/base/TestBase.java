package com.ryanair.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ryanair.utils.TestUtils;

/**
 * @author dmahadev
 *
 */
public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	
	
	public String fileName,imgPath,error;
	public static Logger logger = LogManager.getLogger();
		 

	
	/**
	 * Constructor
	 */
	public TestBase(){
		
		try{
			
			//Read the properties file 
			prop = new Properties();
			FileInputStream ip = new FileInputStream("config"+File.separator+"config.properties");
			prop.load(ip);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Initialization Method
	 */
	public static void initialization(){
		String browsername = prop.getProperty("browser");
		
		//provide the path for the respective drivers correctly
		
		if(browsername.equals("chrome")){
			System.setProperty("webdriver.chrome.driver","externals"+File.separator+"chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browsername.equals("firefox")){
			System.setProperty("webdriver.firefox.driver", "externals"+File.separator+"geckodriver.exe");
		}
		
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait= new WebDriverWait(driver, TestUtils.EXPLICIT_WAIT);
		
	}
	
	
	/** 
	/**
		 * Common method to handle script exception
		 * logger.error should be in 3rd place(to get message even if captchascreenshot method fails) 
		 */
		public void handleException(Throwable exception)
		{
			fileName=this.getClass().getSimpleName();
			error=exception.getMessage();
			logger.error(error);
			captureScreenShotOnFailure(fileName);
			imgPath=getImgPath(fileName);
			logger.error(fileName+" execution failed: <a href='"+imgPath+"' target=blank><img src='"+imgPath+"' height="+20+" width="+40+" /></a>");
			Assert.fail(error, exception);
		}
		
		/**
		 * Get project Path to display screen through testNg report
		 * @return
		 */
		public String getImgPath(String fileName){
			String[] projectPath = null;

			String regEx = Pattern.quote(System.getProperty("file.separator"));
			projectPath = System.getProperty("user.dir").trim().split(regEx);
			String projectname=projectPath[projectPath.length-1];
			return (System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+ fileName + ".jpg").replaceAll("projectPath", projectname);
		}

		
		/**
		 * Captures screenshot on failure
		 * @param filename	 
		 */
		public void captureScreenShotOnFailure(String filename) {
			try {
				File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File(System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+ filename + ".jpg"));
			}
			catch( IOException e) {
				logger.error("Failed to capture screenshot: " + e.getMessage());
			}
		}
	 

	
}
