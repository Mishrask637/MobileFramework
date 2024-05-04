package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class AmazonHomePage {

	AppiumDriver driver;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
	WebElement skipSignin;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_search_icon")
	WebElement searchIcon;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_search_hint_view")
	WebElement searchBar;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	WebElement searchField;

	@FindBy(xpath = "//android.widget.Button[@text=\"mobile\"]")
	WebElement mobileElemet;

	@FindBy(xpath = "//android.view.View[@resource-id=\"search\"]/android.view.View[3]/android.view.View")
	WebElement firstProduct;

	@FindBy(id = "add-to-cart-button")
	WebElement addToCart;

	@FindBy(xpath = "//android.widget.Button[@text=\"X\"]")
	WebElement done;

	@FindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.amazon.mShop.android.shopping:id/bottom_tab_button_icon\"])[3]")
	WebElement cartIcon;

	@FindBy(xpath = "//android.widget.Button[@text=\"Delete\"]")
	WebElement deleteIcon;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Home Tab 1 of 4\"]")
	WebElement homeIcon;

	public String scrollStr = "\"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"?\"))\"";

	public AmazonHomePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchMobileAndAddToCart() throws InterruptedException {
		skipSignin.click();
		searchIcon.isDisplayed();
		searchBar.click();
		searchField.sendKeys("Mobile" + Keys.ENTER);
		mobileElemet.click();
		firstProduct.click();
		driver.findElement(AppiumBy.androidUIAutomator(scrollStr.replace("?", "\"Add to Cart  \""))).click();
		done.click();
	}

	public void removeItemFromcart() {

		cartIcon.click();
		driver.findElement(AppiumBy.androidUIAutomator(scrollStr.replace("?", "\"Delete\""))).click();
		homeIcon.click();
	}
}
