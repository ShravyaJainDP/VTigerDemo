package com.autodeskcrm.orgtest;

import java.awt.Robot;

import org.apache.poi.poifs.crypt.dsig.KeyInfoKeySelector;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.autodeshcrm.genericutils.BaseCalss;
import com.autodeshcrm.genericutils.ExcelUtil;
import com.autodeshcrm.genericutils.FileUtils;
import com.autodeshcrm.genericutils.WebUtils;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

public class DeleteOrganizationTest extends BaseCalss{

@Test
public void deleteOrganization() throws Throwable
{
	
	/* reading data from excel file */
	String org_name=excel.readData("org",1,2)+"_"+web.getRandomNumber();
	String org_Type = excel.readData("org", 1, 3);
	String org_industry = excel.readData("org", 1, 4);
	String expectedMsg=excel.readData("org",6,3);
	
	
	/*Step 3:Navigate to org page */
	driver.findElement(By.linkText("Organizations")).click();
	
	/*Step 4: navigate to organization create page*/
	driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	
	/*Step 5:Creating organization */
	driver.findElement(By.name("accountname")).sendKeys(org_name);
	
	WebElement  swb1 = driver.findElement(By.name("accounttype"));
	web.selectEleByValue(swb1, org_Type);
			
	WebElement  swb2 = driver.findElement(By.name("industry"));
	web.selectEleByValue(swb2, org_industry);
	
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	/* Step 6 verify the create organization */
	String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

	Assert.assertTrue(actOrgName.contains(org_name));
	
	/*Step 7: Navigate back to organization page */
	driver.findElement(By.linkText("Organizations")).click();
	
	/*Step 8: Search the created organization name */
	driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(org_name);
	
	WebElement swb3=driver.findElement(By.xpath("//select[@name='search_field']"));
	web.selectEleByName(swb3, "Organization Name");
	//search path--//input[@value=' Search Now ']//ancestor::table[@class='searchUIBasic small']
	driver.findElement(By.xpath("//input[@value=' Search Now ']")).click();
	
	/*Step 9: Deleting the created organization */
	String xpath="//a[text()='"+org_name+"']/../preceding-sibling::td/input[@name='selected_id']";
	driver.findElement(By.xpath(xpath)).click();
	
	driver.findElement(By.xpath("//input[@value='Delete']")).click();
	web.alertOk(driver);
	
	/* Step 10: Verifying the delete operation */
	/* //span[contains(text(),'No Organization Found')] */
	driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(org_name);
	driver.findElement(By.xpath("//input[@value=' Search Now ']")).click();
	String msg=driver.findElement(By.xpath("//span[@class='genHeaderSmall']")).getText();
	Assert.assertEquals(msg,expectedMsg);
	
	
	
}
}
