package com.autodeskcrm.contacttest;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autodeshcrm.genericutils.BaseCalss;

public class CreateContactWtOrgTest extends BaseCalss{
	
	
@Test
public void createContact() throws Throwable
{
	/* read datafrom excel sheet */
	String org_name=excel.readData("org",1,2)+"_"+web.getRandomNumber();
	String org_Type = excel.readData("org", 1, 3);
	String org_industry = excel.readData("org", 1, 4);
	String contact_name=excel.readData("contact",1,5)+"_"+web.getRandomNumber();
	
	
	web.waitPage(driver);
	/* Step3 navigate to organization page */
	driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();

	/* Step 4: creating organization */
	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(org_name);
	
	WebElement  swb1 = driver.findElement(By.name("accounttype"));
	web.selectEleByValue(swb1, org_Type);
			
	WebElement  swb2 = driver.findElement(By.name("industry"));
	web.selectEleByValue(swb2, org_industry);
	
	
	driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
	
	/*Step 5 varification */
	String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

	Assert.assertTrue(actOrgName.contains(org_name));
	
	/* Step 6:navigate to contact page */
	driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	
	String parent=driver.getTitle();
	
	/* Step 7: Create contact for the organization */

	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	driver.findElement(By.name("lastname")).sendKeys(contact_name);
	
	driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
	/*open new tab */
	web.switchToNewTab(driver, "specific_contact_account_address");
	
	driver.findElement(By.name("search_text")).sendKeys(org_name);
	driver.findElement(By.name("search")).click();
	driver.findElement(By.linkText(org_name)).click();
	
	/* come back to parent window */
	web.switchToNewTab(driver, "Administrator - Contacts");
	
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	/* verifying the contact */
	String actconatct = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	Assert.assertTrue(actconatct.contains(contact_name));
	
	

	
	
}

}
