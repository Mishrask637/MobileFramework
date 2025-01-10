package pageobjects;

import baseclass.Driver;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Helper;

import java.time.Duration;

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

	@FindBy(xpath = "(//android.widget.Button)[2]")
	WebElement mobileElement;

	@FindBy(xpath = "//android.view.View[@resource-id=\"search\"]/android.widget.TextView")
	WebElement firstProduct;

	@FindBy(xpath = "//android.widget.Button[contains(@text,\"Add to Cart\")]")
	WebElement addToCart;

	@FindBy(xpath = "//android.widget.Button[@text='DONE']")
	WebElement done;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/cart_count")
	WebElement cartIcon;

	@FindBy(xpath = "//android.widget.Button[@text='Decrease quantity by one']")
	WebElement deleteIcon;

	@FindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.amazon.mShop.android.shopping:id/bottom_tab_button_icon\"])[1]")
	WebElement homeIcon;

	@FindBy(xpath="//android.widget.Button[@resource-id=\"ax-mbs-close-white\"]")
	WebElement xButton;

	public AmazonHomePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchMobileAndAddToCart() {
		try{
			skipSignin.click();
		}
		catch (Exception e){
			System.out.println("Element is not displayed");
		}
		searchIcon.isDisplayed();
		searchBar.click();
		new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(searchField)).isDisplayed();
		searchField.sendKeys("iphone");
		mobileElement.click();
		Helper.scrollUntilElementIsVisibleAndClick(Driver.getDriver(),firstProduct);
		Helper.scrollUntilElementIsVisibleAndClick(Driver.getDriver(),addToCart);
		try{
			done.click();
		}
		catch (Exception e){
			xButton.click();
		}
	}

	public void removeItemFromCart() {

		cartIcon.click();
		Helper.scrollUntilElementIsVisibleAndClick(Driver.getDriver(),deleteIcon);
		homeIcon.click();
		homeIcon.click();
		new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(searchIcon)).isDisplayed();
	}
}
