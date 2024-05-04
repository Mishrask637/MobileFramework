package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
			features = "src/test/resources/features/HandleDynamicWebTable.feature",
			glue = {"stepdefinitions","hooks"},
			dryRun = false,
			plugin = {"pretty","html:reports/html/cucumberReports.html",
					 "json:reports/json/cucumberReports.json"},
			monochrome = true
//			publish = true
		)
public class HandleDynamicWebTableRunner extends AbstractTestNGCucumberTests{}
