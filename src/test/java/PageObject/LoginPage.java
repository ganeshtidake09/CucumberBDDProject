package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
WebDriver ldriver;
	
	public LoginPage(WebDriver rDriver)//constructor
	{
		ldriver = rDriver;
		PageFactory.initElements(rDriver,this);
	}
	
	@FindBy(xpath = "//input[@id='Email']")
	WebElement email;
	
	@FindBy(xpath ="//input[@id='Password']")
	WebElement password;
	
	@FindBy(xpath ="//button[contains(text(),'Log in')]")
	WebElement LoginBtn;
	
	@FindBy(linkText = "Logout")
	WebElement Logout;
	public void enterEmail(String emailAdd)
	{
		email.clear();
		email.sendKeys(emailAdd);
		
	}
	public void enterPassword(String pwd)
	{
		password.clear();
		password.sendKeys(pwd);
		
	}
	public void clickOnLoginButton()
	{
		LoginBtn.click();
	}
	public void clickOnLogOutButton()
	{
		LoginBtn.click();
	}

}
