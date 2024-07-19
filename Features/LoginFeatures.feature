Feature: Login 

@Sanity 
Scenario: Successful Login with Valid Credentials 
	Given User Launch Chrome browser 
	When User opens URL "http://admin-demo.nopcommerce.com/login" 
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login 
	Then Page Title should be "Dashboard / nopCommerce administration" 
	When User click on Log out link 
#	Then Page Title should be "Your store. Login1" #this method is only for failing after fail this step this method then it will  take screenshot 
	Then Page Title should be "Your store. Login" 
	And close browser
	
@regression
Scenario Outline:Successful Login with Valid Credentials #it is used for Data driven testing
	Given User Launch Chrome browser 
	When User opens URL "http://admin-demo.nopcommerce.com/login" 
	And User enters Email as "<email>" and Password as "<password>" 
	And Click on Login 
	Then Page Title should be "Dashboard / nopCommerce administration" 
	When User click on Log out link 
	Then Page Title should be "Your store. Login" 
	And close browser
	
Examples:
|email|password|
|admin@yourstore.com|admin|
|test@yourstore.com|admin| # this scenario is negative thats why this steps is fail

