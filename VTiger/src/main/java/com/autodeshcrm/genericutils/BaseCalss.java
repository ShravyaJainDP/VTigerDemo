package com.autodeshcrm.genericutils;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseCalss {
	static
	{
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
	}

	public WebUtils web=new WebUtils();
	public ExcelUtil excel=new ExcelUtil();
	public FileUtils file=new FileUtils();
	public DBUtils dbu=new DBUtils();
	public WebDriver driver;
	
	@BeforeSuite
	public void configuringBS() throws Throwable
	{
		dbu.getDBConnection();
	}
	
	@BeforeClass
	public void configuringBC() throws Throwable
	{
		String browser=file.getPropertyKeyValue("BROWSER");
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
	}
/*	@Parameters("browser")
	@BeforeTest
	public void configureBT(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
	}
*/	
	@BeforeMethod
	public void congigureBM() throws Throwable
	{
		String url=file.getPropertyKeyValue("URL");
		String username=file.getPropertyKeyValue("UN");
		String pwd=file.getPropertyKeyValue("PWD");
		
		driver.get(url);
		driver.manage().window().maximize();
		web.waitPage(driver);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();
	}
	
	@AfterMethod
	public void configureAM()
	{
		WebElement lagout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		web.mouseHovering(driver, lagout);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	//	driver.close();
	}
	
	@AfterClass
	public void configureAC()
	{
		driver.close();
	}
	
	@AfterSuite
	public void configuringAS() throws Throwable
	{
		//dbu.closeConnection();
	}
}
