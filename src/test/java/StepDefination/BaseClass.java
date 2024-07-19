 package StepDefination;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;


/* Parent class */
public class BaseClass 
{
	public WebDriver driver;
	public LoginPage loginpg;
	public AddNewCustomerPage addNewCustPg;
	public SearchCustomerPage SearchCustPg;
	public static Logger log;
	public ReadConfig readConfig;

	//For generating random email id for login(Everytime new)
	public String generateEmailId()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}

}
