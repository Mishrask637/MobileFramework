package hooks;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import baseclass.AppiumService;
import baseclass.Driver;
import utility.GenerateCucumberReports;
import utility.Helper;

public class CucumberHooks {

	static LocalDateTime time = LocalDateTime.now();
	public static String screenshotPath = System.getProperty("user.dir")+"/screenshots/"+time.getDayOfMonth()+"_"+time.getMonth()+"_"+time.getYear();

	@BeforeAll
	public static void startAppium() {
		AppiumService.startService();
		Driver.initDriver();
		Helper.createDir(screenshotPath);
	}

	@Before
	public void setup() {
		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@After
	public void tearDown(Scenario scenario) throws FileNotFoundException {
		if(scenario.isFailed()) {
			byte[] ss = null;
			FileOutputStream fos = new FileOutputStream(screenshotPath + "\\" + scenario.getName() + "_" + time.getHour() + "_" + time.getMinute() + "_" + time.getSecond() + ".png");
			try {
				ss = Helper.takeScreenShot();
				fos.write(ss);
				fos.flush();
				fos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			scenario.attach(ss, "image/png", scenario.getName());
		}
			System.out.println("Terminating app....");
			Driver.getDriver().quit();
            Driver.driver.remove();
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("In After All block....");
        GenerateCucumberReports reports = new GenerateCucumberReports();
		reports.generateCucumberReports();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		AppiumService.stopService();
	}
}
