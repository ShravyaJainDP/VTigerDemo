package com.autodeskcrm.orgtest;

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

public class CreateOrganizationTest extends BaseCalss{

	@Test
public void createOrg() throws Throwable
{
		/* reading data from excelfile */
		String org_name=excel.readData("org",1,2)+"_"+web.getRandomNumber();
		String org_Type = excel.readData("org", 1, 3);
		String org_industry = excel.readData("org", 1, 4);
		
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
}
}
