package pageobjects;

import baseclass.Driver;
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

	@FindBy(id = "in.amazon.mShop.android.shopping:id/skip_sign_in_button")
	WebElement skipSignin;

	@FindBy(id = "in.amazon.mShop.android.shopping:id/chrome_action_bar_search_icon")
	WebElement searchIcon;

	@FindBy(id = "in.amazon.mShop.android.shopping:id/chrome_search_hint_view")
	WebElement searchBar;

	@FindBy(id = "in.amazon.mShop.android.shopping:id/rs_search_src_text")
	WebElement searchField;

	@FindBy(xpath = "//android.widget.Button[@text=\"mobile\"]")
	WebElement mobileElemet;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Results Check each product page for other buying options.\"]/..//following-sibling::android.view.View[1]")
	WebElement firstProduct;

	@FindBy(xpath = "//android.widget.Button[contains(@text,\"Add to Cart\")]")
	WebElement addToCart;

	@FindBy(xpath = "//android.widget.TextView[@text='DONE']")
	WebElement done;

	@FindBy(id = "in.amazon.mShop.android.shopping:id/cart_count")
	WebElement cartIcon;

	@FindBy(xpath = "//android.widget.Button[@text=\"Delete\"]")
	WebElement deleteIcon;

	@FindBy(xpath = "(//android.widget.ImageView[@resource-id=\"in.amazon.mShop.android.shopping:id/bottom_tab_button_icon\"])[1]")
	WebElement homeIcon;

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
		searchField.sendKeys("Mobile");
		new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(mobileElemet));
		mobileElemet.click();
		Helper.scrollUntilElementIsVisibleAndClick(Driver.getDriver(),firstProduct);
		Helper.scrollUntilElementIsVisibleAndClick(Driver.getDriver(),addToCart);
		done.click();
	}

	public void removeItemFromCart() {

		cartIcon.click();
		Helper.scrollUntilElementIsVisibleAndClick(Driver.getDriver(),deleteIcon);
		homeIcon.click();
	}
}
