package baseclass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Driver {

	public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

	private Driver() {
	}

	public static AppiumDriver initDriver() {
		UiAutomator2Options androidOptions = new UiAutomator2Options();
		androidOptions.setAppPackage("in.amazon.mShop.android.shopping");
		androidOptions.setAppActivity("com.amazon.mShop.splashscreen.StartupActivity");
		androidOptions.setDeviceName("Pixel 7 Pro");
		androidOptions.setNoReset(true);
		androidOptions.setNewCommandTimeout(Duration.ofSeconds(10));

		try {
			driver.set(new AndroidDriver(new URL("http://127.0.0.1:4723"), androidOptions));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver.get();
	}

	public static AppiumDriver initAppiumChromeDriver() {
		UiAutomator2Options androidOptions = new UiAutomator2Options();
		androidOptions.withBrowserName("Chrome");
		androidOptions.setDeviceName("Pixel 7 Pro");
		androidOptions.setNoReset(true);
		androidOptions.setNewCommandTimeout(Duration.ofSeconds(10));

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
