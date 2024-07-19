package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage
{
	WebDriver ldriver;
	
	public SearchCustomerPage(WebDriver rDriver)//constructor
	{
		ldriver = rDriver;
		PageFactory.initElements(rDriver,this);
	}
	
	@FindBy(id="SearchEmail")  //inspect Email of search on this https://admin-demo.nopcommerce.com/Admin/Customer/List
	WebElement emailAdd;
	
	@FindBy(id="search-customers") //insepect search
	WebElement SearchBtn;
	
	@FindBy(xpath="//table[@id='customers-grid']")//xpath="//table[@role='grid'] if previous is not working
	WebElement searchResult;
	
	@FindBy(xpath ="//table[@id='customers-grid']//tbody/tr") //no of rows
	List<WebElement> tableRows;
	
//	@FindBy(xpath ="//table[@id='customers-grid']//tbody/tr[1]/td") //no of columns 
//	List<WebElement> tableColumns; //one than more elements that why list
	
    //search element by Name 
	@FindBy(id="SearchFirstName")
	WebElement firstName;
	
	@FindBy(id ="SearchLastName")
	WebElement lastName;
	
	//Action method to enter email address
	public void enterEmailAdd(String email)
	{
		emailAdd.sendKeys(email);
	}
	
	//Action method to perform click action on search button
	public void clickOnSearchButton()
	{
		SearchBtn.click();;
	}
	
	public boolean searchCustomerByEmail(String email)
	{
		boolean found = false;
		
		//total no of rows in a grid
		int ttlRows = tableRows.size();
		
		//total no of Columns in a grid
		//int ttlColumns = tableColumns.size();
		
		//to iterate all the rows of the grid
		for(int i=1;i<=ttlRows;i++) //i=1 because first row is header
		{
			WebElement webElementEmail = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i  + "]/td[2]"));
			String actualEmailAdd = webElementEmail.getText();
			
			if(actualEmailAdd.equals(email))
			{
				found=true;
			}
		
		}
		
		return found;
	}
	
////////////////////Search customer by name////////////////////
	
	//Action method to perform click action on First name button
	public void enterFirstName(String firstNameText)
	{
		firstName.sendKeys(firstNameText);
	}
	
	//Action method to perform click action on Last name button
	public void enterLastName(String lastNameText)
	{
		firstName.sendKeys(lastNameText);
	}
	
	public boolean searchCustomerByName(String name)
	{
		boolean found = false;
		
		//total no of rows in a grid
		int ttlRows = tableRows.size();
		

		
		//to iterate all the rows of the grid
		for(int i=1;i<=ttlRows;i++) //i=1 because first row is header
		{
			WebElement webElementName = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i  + "]/td[3]"));//td3 is for name
			String actualName = webElementName.getText();
			
			if(actualName.equals(name))
			{
				found=true;
				break;
			}
		
		}
		
		return found;
	}

}
