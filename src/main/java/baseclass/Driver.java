package baseclass;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Driver {

	public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

	private Driver() {
	}

	public static AppiumDriver initDriver() {

//		DesiredCapabilities caps = new DesiredCapabilities();
//
//		caps.setCapability("appium:deviceName", "One Plus 12");
//		caps.setCapability("appium:platformName", "Android");
//		caps.setCapability("appium:platformVersion", "14");
//		caps.setCapability("appium:udid", "emulator-5554");
//		caps.setCapability("appium:appPackage", "in.amazon.mShop.android.shopping");
//		caps.setCapability("appium:appActivity", "com.amazon.windowshop.home.HomeLauncherActivity");
////		caps.setCapability("appium:browserName", "Chrome");
//		caps.setCapability("appium:automationName", "Uiautomator2");
//		caps.setCapability("appium:noReset", "true");

		UiAutomator2Options androidOptions = new UiAutomator2Options();

		androidOptions
				.setApp(System.getProperty("user.dir") + "\\src\\test\\resources\\apk\\amazon-shopping-28-9-2-100.apk");
		androidOptions.setDeviceName("Pixel 8 Pro");

		try {
			driver.set(new AndroidDriver(new URL("http://127.0.0.1:4723"), androidOptions));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return driver.get();

	}

	public static AppiumDriver getDriver() {
		return driver.get();
	}

}
