package com.autodeshcrm.genericutils;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import com.google.common.io.Files;
	/**
	 * 
	 * @author Shravya 
	 *
	 */

	public class WebUtils 
	{
		private static WebDriverWait wait;
		private static Actions action;
		private static Select select;
		
		public void waitPage(WebDriver driver)
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		/**
		 * This method is used to take screen shot
		 * @param driver
		 * @param filename
		 * @throws Throwable
		 */
		public void takeScreenImage(WebDriver driver,String filename) throws Throwable
		{
			TakesScreenshot pic=(TakesScreenshot)driver;
			File src=pic.getScreenshotAs(OutputType.FILE);
			FileOutputStream des=new FileOutputStream(filename);
			Files.copy(src, des);
		}
		
		/**
		 * This method is used for Explicit wait - title is visible
		 * @param driver
		 * @param title
		 */
		public void expectedTitle(WebDriver driver,String title)
		{
			wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.titleContains(title));
		}
		
		/**
		 * This method is used forExplicit wait - visibility of web element 
		 * @param driver
		 * @param element
		 */
		public void expectedWebElement(WebDriver driver,WebElement element)
		{
			wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		/**
		 * This method is for mouse-overing
		 * @param driver
		 * @param element
		 */
		public void mouseHovering(WebDriver driver,WebElement element)
		{
			action=new Actions(driver);
			action.moveToElement(element).perform();
		}
		
		/**
		 * This method is for double click
		 * @param driver
		 * @param element
		 */
		public void mouseDoubleClick(WebDriver driver,WebElement element)
		{
			action=new Actions(driver);
			action.doubleClick(element).perform();
		}
		/**
		 * For Right click on mouse
		 * @param driver
		 * @param element
		 */
		public void rightClick(WebDriver driver,WebElement element)
		{
			action=new Actions(driver);
			action.contextClick(element).perform();;
		}
		/**

		 */
		public void forDragAndDrop(WebDriver driver,WebElement source,WebElement target)
		{
			action=new Actions(driver);
			action.dragAndDrop(source, target).perform();;
		}
		/**
		 * For Select by id
		 * @param element
		 * @param index
		 */
		public void selectEleByIndex(WebElement element,int index)
		{
			select =new Select(element);
			select.selectByIndex(index);
		}
		/**
		 * Select Element By name
		 * @param element
		 * @param visibletext
		 */
		public void selectEleByName(WebElement element,String visibletext)
		{
			select= new Select(element);
			select.selectByVisibleText(visibletext);
		}
		/**
		 * Select element byvalue
		 * @param element
		 * @param value
		 */
		public void selectEleByValue(WebElement element,String value)
		{
			select = new Select(element);
			select.selectByValue(value);
		}
		
		/**
		 * This method used to accept
		 * @param driver
		 */
		public void alertOk(WebDriver driver)
		{
			driver.switchTo().alert().accept();
		}
		/**
		 * This method used to dismiss alert
		 * @param driver
		 */
		public void alertDismis(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}
		/**
		 * This method used to generate Random number
		 * @return
		 */
		public int getRandomNumber()
		{
			Random ran=new Random();
			return ran.nextInt(1000);
		}
		/**
		 * 
		 * @param driver
		 * @param pageTitle
		 */
		public void switchToNewTab(WebDriver driver, String pageTitle) {
			 Set<String> set = driver.getWindowHandles();
			 Iterator<String> it = set.iterator();
			 
			 while (it.hasNext()) {
				 String winID = it.next();
				 driver.switchTo().window(winID);
			      String currentPageTitle = driver.getTitle();
			      if(currentPageTitle.contains(pageTitle)) {
			    	  break;
			      }
				
			}
		}
		public void keyFunction(WebDriver driver)
		{
			
		}
		
		
	}
