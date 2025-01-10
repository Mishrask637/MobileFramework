package baseclass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import static baseclass.BaseClass.readProperties;

public class Driver {

	public static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

	private Driver() {
	}

	public static AndroidDriver initDriver() {
		Properties prop = readProperties();
		UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
		uiAutomator2Options.setUdid(prop.getProperty("udid"));
		uiAutomator2Options.setAppPackage(prop.getProperty("appPackage"));
		uiAutomator2Options.setAppActivity(prop.getProperty("appActivity"));
		uiAutomator2Options.setNoReset(true);
		uiAutomator2Options.setDeviceName(prop.getProperty("deviceName"));
		uiAutomator2Options.setNewCommandTimeout(Duration.ofSeconds(30));
		uiAutomator2Options.setCapability("appium:shouldTerminateApp",true);

		try {
			driver.set(new AndroidDriver(new URL(prop.getProperty("url")), uiAutomator2Options));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		return driver.get();
	}

	public static AppiumDriver initAppiumChromeDriver() {
		Properties prop = readProperties();
		UiAutomator2Options androidOptions = new UiAutomator2Options();
		androidOptions.withBrowserName(prop.getProperty("browser"));
		androidOptions.setDeviceName(prop.getProperty("deviceName"));
		androidOptions.setNoReset(true);
		androidOptions.setNewCommandTimeout(Duration.ofSeconds(10));

		try {
			driver.set(new AndroidDriver(new URL(prop.getProperty("url")), androidOptions));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver.get();

	}

	public static AndroidDriver getDriver() {
		return driver.get();
	}

}
