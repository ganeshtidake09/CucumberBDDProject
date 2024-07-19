package TestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		
	   //features = ".//Features/LoginFeatures.feature", //for executing LoginFeatures  file
	  //features = ".//Features/Customers.feature.feature", //for executing Customers feature file
	  //features = ".//Features/",        //it is used for executing all feature files which is in Features folder
		features = {".//Features/LoginFeatures.feature",".//Features/Customers.feature.feature"},//Execute two feature files at a time
		
		glue="StepDefinition",
		dryRun = false,//it will check the step def methods implementation then use false 
		//if you want to check the implementation is present or not in feature file then simply use dryrun=true
		monochrome = true, //for readability
		//tags = "@Sanity", //Scenarios under @sanity tag will execute.
		//tags = "@regression",//Scenarios under @regression tag will execute.
		//tags = "@Sanity or @regression", //Will run Scenario tagged with sanity or regression
		//tags = "@Sanity and @regression", //Will run Scenario which is tagged with sanity as wll as regression
	    //tags = "@Sanity and not @regression", //Will run Scenario which is tagged with sanity but not regression
		
		plugin = {"pretty","html:target/cucumber-reports/reports.html"}//using for generating reports
		)

public class Run 
{
	/*This class will be empty*/

}
