package com.autodeskcrm.pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	/* user name text field*/
	@FindBy(name="user_name")
	private WebElement user_name;
	/* password text field */
	@FindBy(name="user_password")
	private WebElement user_password;
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	/*return user_name */
	public WebElement getUserName()
	{
		return user_name;
	}
	/* return password */
	public WebElement getUserPwd()
	{
		return user_password;
	}
	/* click on loginbutton */
	public void clickLogin()
	{
		loginBtn.click();
	}
	public void login(String user,String pwd)
	{
		user_name.sendKeys(user);
		user_password.sendKeys(pwd);
		loginBtn.click();
	}
	public void login()
	{
		user_name.sendKeys("admin");
		user_password.sendKeys("password123");
		loginBtn.click();
	}
}
