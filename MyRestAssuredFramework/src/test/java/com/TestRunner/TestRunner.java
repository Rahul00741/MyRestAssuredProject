package com.TestRunner;

import org.testng.annotations.AfterClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(


		dryRun = false, 
		features = "src/test/resources/features/POST.feature", 
		glue = {
				"com.StepDefinition" }, 
		monochrome = true, 
		plugin = { "pretty",
					"html:Reports/cucumber-html-report", 
					"rerun:target/cucumber-reports/rerun.txt",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter: ",
					"testng:target/testng-cucumber-reports/cuketestng.xml"
					}

)

public class TestRunner extends AbstractTestNGCucumberTests
 {
	@AfterClass
    public static void extendReport()
  {
//		Reporter.loadXMLConfig("./src/test/resources/Extent_Adapter/extent-config.xml");
//	Reporter.setSystemInfo("user", System.getProperty("user.name"));
//	Reporter.setSystemInfo("os", "Windows");
  }


	
}
