package com.autodeskcrm.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	/* organization navigation link */
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;
	/* Contact navigation link */
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	/* administrator mouse hovering for logout */
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminimage;
	/* singout */
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement singout;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	/* Get Organization link */
	public WebElement getOrganizationLink()
	{
		return organizationLink;
	}
	/* Get contact link */
	public WebElement getContactLink()
	{
		return contactLink;
	}
	/* get admin mousehovering */
	public WebElement getAdminMH()
	{
		return adminimage;
	}
	/*get signout */
	public WebElement getSignOut()
	{
		return singout;
	}
}
