package hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import baseclass.AppiumService;
import baseclass.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import utility.GenerateCucumberReports;

public class CucumberHooks {

	WebDriver driver;

	@BeforeAll
	public static void startAppium() {
		AppiumService.startService();
	}

	@Before
	public void setup() {
		Driver.initDriver();
		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@After
	public void tearDown() {
		Driver.getDriver().quit();
		Driver.driver.set(null);
	}

	@AfterAll
	public static void afterAll() {
		GenerateCucumberReports reports = new GenerateCucumberReports();
		reports.generateCucumberReports();
		AppiumService.stopService();
	}
}
