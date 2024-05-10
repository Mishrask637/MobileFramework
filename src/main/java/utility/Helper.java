package utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import baseclass.Driver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import io.appium.java_client.android.AndroidDriver;

public class Helper {
	public static void tap(AndroidDriver driver, WebElement element) {
		Point location = element.getLocation();
		Dimension size = element.getSize();
		Point center = getCenterOfElement(location, size);
		PointerInput finger1 = new PointerInput(PointerInput.Kind.MOUSE, "finger1");
		Sequence seq = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(500)))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(seq));
	}

	public static void longPress(AndroidDriver driver, WebElement element) {
		Point location = element.getLocation();
		Dimension size = element.getSize();
		Point center = getCenterOfElement(location, size);
		PointerInput finger1 = new PointerInput(PointerInput.Kind.MOUSE, "finger1");
		Sequence seq = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofSeconds(2)))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Collections.singletonList(seq));
	}

	public static void doubleTap(AndroidDriver driver, WebElement element) {
		Point location = element.getLocation();
		Dimension size = element.getSize();
		Point center = getCenterOfElement(location, size);
		PointerInput finger1 = new PointerInput(PointerInput.Kind.MOUSE, "finger1");
		Sequence seq = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(100)))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(100)))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(100)))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Collections.singletonList(seq));
	}

	public static void scroll(AndroidDriver driver) {
		Dimension size = driver.manage().window().getSize();
		int startX = size.getWidth() / 2;
		int startY = size.getHeight() / 2;
		int endX = startX;
		int endY = (int) (size.getHeight() * 0.50);
		PointerInput finger1 = new PointerInput(PointerInput.Kind.MOUSE, "finger1");
		Sequence seq = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(200)))
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Collections.singletonList(seq));
	}

	public static void scrollUntilElementIsVisible(AppiumDriver driver, WebElement element) {
		Dimension size = driver.manage().window().getSize();
		int startX = size.getWidth() / 2;
		int startY = (int) (size.getHeight() * 0.87);
		int endX = startX;
		int endY = (int) (size.getHeight() * 0.17);
		for(int i=0;i<5;i++) {
            try{
					element.isDisplayed();
					break;
				}
				catch(Exception e) {
					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence seq = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
							.addAction(finger1.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), endX, endY))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
					driver.perform(Collections.singletonList(seq));
				}
		}
	}

	public static void scrollUntilElementIsVisibleAndClick(AppiumDriver driver, WebElement element) {
		Dimension size = driver.manage().window().getSize();
		int startX = size.getWidth() / 2;
		int startY = (int) (size.getHeight() * 0.87);
		int endX = startX;
		int endY = (int) (size.getHeight() * 0.17);
		for(int i=0;i<5;i++) {
			try{
				element.click();
				break;
			}
			catch(Exception e) {
				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence seq = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
						.addAction(finger1.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), endX, endY))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
				driver.perform(Collections.singletonList(seq));
			}
		}
	}

	private static Point getCenterOfElement(Point location, Dimension size) {
		return new Point(location.getX() + size.getWidth() / 2, location.getY() + size.getHeight() / 2);
	}

	public static byte[] takeScreenShot() throws IOException {
		byte[] ss = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
		return ss;
	}

	public static void createDir(String dirPath){
		System.out.println("Creating Directory : "+dirPath);
		try {
			File dir = new File(dirPath);
			boolean created = false;
			if (!dir.exists()) {
				created = dir.mkdirs();
			}
			System.out.println("Directory created : "+created);
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }

	public static void deleteDir(String dirPath){
		File dir = null;
		dir = new File(dirPath);
		if(dir.exists()){
			File[] files = dir.listFiles();
			for(File file : files){
				file.delete();
			}
			dir.delete();
		}
	}

	public static void createFileIfNotExists(String filePath){
		File file = new File(filePath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
        try {
            file.createNewFile();
        } catch (IOException e) {
			e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}