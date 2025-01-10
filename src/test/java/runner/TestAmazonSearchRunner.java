package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features/AmazonSearch.feature",
		glue = { "stepdefinitions", "hooks" },
		dryRun = false,
		plugin = { "pretty", "html:target/reports/html/cucumberReports.html",
				"json:target/reports/json/cucumberReports.json" },
		monochrome = true
)
public class TestAmazonSearchRunner extends AbstractTestNGCucumberTests {
}

