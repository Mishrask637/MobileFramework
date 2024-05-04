package utility;

import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
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

	private static Point getCenterOfElement(Point location, Dimension size) {
		return new Point(location.getX() + size.getWidth() / 2, location.getY() + size.getHeight() / 2);
	}

}
