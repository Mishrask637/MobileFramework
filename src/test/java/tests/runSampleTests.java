package tests;

import org.testng.annotations.Test;

import baseclass.Driver;
import io.appium.java_client.AppiumDriver;

public class runSampleTests {

	@Test
	public void sampleTest() {

		AppiumDriver driver = Driver.initDriver();

		driver.get("https://www.google.co.in");

	}

}
