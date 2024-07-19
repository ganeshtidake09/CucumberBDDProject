package StepDefination;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

/* Child class of base class*/
public class StepDef extends BaseClass
{
	//Here used two setup methods for hooking in that we use multiple Before annotations by using order keyword 
//	@Before(order=1)
	@Before("@Sanity")//this is conditional hook in that we add the condition @sanity in that the scenarios are under sanity tags this will execute only
	public void setup()
	{
		
		readConfig = new ReadConfig();
		
		//Initialization logger
		log = (Logger) LogManager.getLogger("StepDef"); //Mentioned this class name(StepDef) in property file
		System.out.println("Setup method executed");
		
		
		String browser = readConfig.getBrowser();
		
		//launch browser
				switch(browser.toLowerCase())
				{
				case "chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;

				case "msedge":
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
					break;

				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					break;
				default:
					driver = null;
					break;

				}
				
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		log.info("Setup1 executed...");
		
	}
	
//	@Before(order=0)
	@Before("@regression") //all scenarios executed under regression tag  scenarios.
	public void setup2()
	{
		System.out.println("Setup method executed");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); //create memory for chromebrowser
		
		loginpg = new LoginPage(driver);
		SearchCustPg = new SearchCustomerPage(driver);
		log.info("User launch Chrome browser");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) 
	{
		driver.get(url);//meet from feature file 
		log.info("open URl.......");
	   
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailAdd, String password) 
	{
		loginpg.enterEmail(emailAdd);//getting from feature files
		loginpg.enterPassword(password);
		log.info("email and password eneterd...");
	   
	}

	@When("Click on Login")
	public void click_on_login() 
	{
		loginpg.clickOnLoginButton(); //page object claass i used
		log.info("Login button click....");
	    
	}
//////////////////////Login Features////////////
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) 
	{
		String actualTitle = driver.getTitle();
		
		if(actualTitle.equals(expectedTitle))
		{
			log.warn("Test passed : Login Feature : Page title matched.");
			Assert.assertTrue(true); //pass
		}
		else
		{
		
			Assert.assertTrue(false);//fail
			log.warn("Test failed : Login Feature : Page title not matched.");

		}
	   
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() 
	{
		loginpg.clickOnLogOutButton();
		log.info("User clicked on logout button");
	   
	}

	@Then("close browser")
	public void close_browser()
	{
		driver.close();
		log.info("Browser closed");
	    
	}
//////////////Add New Customers////////////////
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("user can view dashboad test passed.");
			Assert.assertTrue(true);

		}
		else
		{
			Assert.assertTrue(false);
			log.warn("user can view dashboad test failed.");

		}
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() 
	{
		addNewCustPg.clickOnCustomersMenu();
		log.info("customer menu clicked");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item()
	{
		addNewCustPg.clickOnCustomersMenuItem();
		log.info("customer menu item clicked");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("click on Add new button")
	public void click_on_add_new_button() 
	{
		log.info("Clicked on add new button..");
		addNewCustPg.clickOnAddnew();

	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() 
	{
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";

		if(actualTitle.equals(expectedTitle))
		{
			log.info("User can view Add new customer page-passed");
			Assert.assertTrue(true);//pass

		}
		else
		{
			Assert.assertTrue(false);//fail
			log.info("User can view Add new customer page-failed");

		}
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
		//addNewCustPg.enterEmail("cs129@gmail.com");
	//	addNewCustPg.enterEmail("test123@gmail.com"); //Change the id for different scenario
	
		addNewCustPg.enterEmail(generateEmailId() + "gmail.com"); //call method from base class and concate with gmail.com in that method random email address will be generates
		addNewCustPg.enterPassword("test1");
		addNewCustPg.enterFirstName("Prachi");
		addNewCustPg.enterLastName("Gupta");
		addNewCustPg.enterGender("Female");
		addNewCustPg.enterDob("6/13/1988");
		addNewCustPg.enterCompanyName("CodeStudio");
		addNewCustPg.enterAdminContent("Admin content");
		addNewCustPg.enterManagerOfVendor("Vendor 1");
		
		log.info("Customer information entered");

	}

	@When("click on Save button")
	public void click_on_save_button() 
	{
		addNewCustPg.clickOnSave();
		log.info("click on Save button");

	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg)
	{

		String bodyTagText = driver.findElement(By.tagName("Body")).getText();
		if(bodyTagText.contains(exptectedConfirmationMsg))
		{
			
			Assert.assertTrue(true);//pass
			log.info("User can view confirmation message-passed");
		}
		else
		{
			Assert.assertTrue(false);//fail
			log.warn("User can view confirmation message-failed");
		}

	}
	
	////////////Search Customer//////////////////////////
	@When("Enter customer EMail")
	public void enter_customer_e_mail() 
	{
		SearchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
		log.info("Enter customer EMail");
	
	}

	@When("Click on search button")
	public void click_on_search_button() {
		SearchCustPg.clickOnSearchButton();
		log.info("Clicked on search button");
	

		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() 
	{
		String expectedEmail = "victoria_victoria@nopCommerce.com";

		//   Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail));

		if( SearchCustPg.searchCustomerByEmail(expectedEmail)==true)
		{
			
			Assert.assertTrue(true);
			log.info("User should found Email in the Search table - passed");
			
		}
		else
       {
			Assert.assertTrue(false);
			log.info("User should found Email in the Search table - failed");
		}


	}
//////////////////Search customer by name/////////////////////////////////////////////////////
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name()
	{
		SearchCustPg.enterFirstName("Victoria");
		log.info("Enter customer first name");
	}
	
	@When("Enter customer LastName")
	public void enter_customer_last_name()
	{
		SearchCustPg.enterLastName("Terces");
		log.info("Enter customer last name");
	}
	
	@Then("User should found name in the Search table")
	public void user_should_found_name_in_the_Search_table()
	{
		String expectedName = "victoria Terces";

		if( SearchCustPg.searchCustomerByName(expectedName)==true)
		{
			log.info("User should found name in the Search table - passed");
			Assert.assertTrue(true);	
		}
		else 
		{		
			Assert.assertTrue(false);
			log.info("User should found name in the Search table - failed");
		}

	}
	
//	@After(order=2) //in after higher value execute first and lower value execute after higher value
	@After
	public void teardown1(Scenario sc) //each scenario after this method will execute and thsi method is fail then scrrenshot 
	{
		System.out.println("Tear Down Method executed");
		if(sc.isFailed()==true)
		{
			//Convert web driver object to TakeScreenshot

			String fileWithPath = "D:\\Cucumber(BDD)\\CucumberBDD\\Screenshot\\failedSceenshot.png";
			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination
			File DestFile=new File(fileWithPath);

			//Copy file at destination

			try 
			{
				FileUtils.copyFile(SrcFile, DestFile);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.quit();
	}
	
/*	@After(order=1)
	public void teardown2() //each scenario after this method will execute 
	{
		System.out.println("Tear Down Method executed");
		driver.quit();
	}
	
	
	@BeforeStep   
	public void beforeStepMethodDemo() //This method will execute before each step 
	{
		System.out.println("This is before step............");
	}

	
	@AfterStep
	public void afterStepMethodDemo()    ////This method will execute after each step 
	{
		System.out.println("This is after step............");
	}
*/
}
