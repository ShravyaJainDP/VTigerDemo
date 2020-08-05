package com.autodeskcrm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autodeshcrm.genericutils.BaseCalss;

public class DeleteContactWithOrgTest extends BaseCalss {
	
@Test	
public void deleteContactWithOrg() throws Throwable
{
	/* reading data from excel file */
	String org_name=excel.readData("org",1,2)+"_"+web.getRandomNumber();
	String org_Type = excel.readData("org", 1, 3);
	String org_industry = excel.readData("org", 1, 4);
	String contact_name=excel.readData("contact", 1,5)+"_"+web.getRandomNumber();
	String expectedMsg=excel.readData("contact",2,6);
	
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
	
	
	/* again navigate on contact page */
	driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	
	driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(contact_name);
	WebElement we3=driver.findElement(By.xpath("//select[@id='bas_searchfield']"));
	web.selectEleByName(we3, "Last Name");
	driver.findElement(By.xpath("(//input[@value=' Search Now '])[1]")).click();
	
	/* selecting the contact to delete */
	
	String xpath="//a[text()='"+contact_name+"']/../preceding-sibling::td/input[@name='selected_id']";
	driver.findElement(By.xpath(xpath)).click();
	
	driver.findElement(By.xpath("//input[@value='Delete']")).click();
	web.alertOk(driver);
	
	/* verifying the delete peration */
	driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(contact_name);
	web.selectEleByName(we3, "Last Name");
	driver.findElement(By.xpath("(//input[@value=' Search Now '])[1]")).click();
	
	String msg=driver.findElement(By.xpath("//span[@class='genHeaderSmall']")).getText();
	Assert.assertEquals(msg,expectedMsg);
	
	
	
	
	
	
	
	
}

}
