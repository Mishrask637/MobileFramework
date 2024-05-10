package pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseclass.Driver;
import io.appium.java_client.AppiumDriver;

public class AutomationPracticePage {

	private AppiumDriver driver;

	@FindBy(xpath = "(//table[@id='countries']/tbody/tr)[1]/td")
	private List<WebElement> tableHeaders;

	@FindBy(xpath = "//table[@id='countries']/tbody/tr")
	private List<WebElement> totalTableRows;

	private String CountryRowCount = "//table[@id='countries']/tbody/tr[?]/td[2]";

	private String rowDetails = "//table[@id='countries']/tbody/tr[?]/td";

	private String checkBox = "//table[@id='countries']/tbody/tr[?]/td/input";

	public AutomationPracticePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int getCountryRowCount(String Country) {

		int totalRows = totalTableRows.size();
		int i;
		for (i = 2; i <= totalRows; i++) {
			String countryName = driver.findElement(By.xpath(CountryRowCount.replace("?", String.valueOf(i))))
					.getText();
			if (Country.equals(countryName)) {
				System.out.println("I Value for Country " + Country + " is " + i);
				return i;
			}
		}
		return 0;
	}

	public void printDetailsOf(String Country) {
		String rowVal = String.valueOf(getCountryRowCount(Country));
		int colCount = driver.findElements(By.xpath(rowDetails.replace("?", rowVal))).size();
		System.out.println("Col count is " + colCount);
		Map<String, String> countryDataMap = new HashMap<String, String>();
		for (int i = 0; i < colCount; i++) {
			String colData = driver.findElements(By.xpath(rowDetails.replace("?", rowVal))).get(i).getText();
			String headerData = tableHeaders.get(i).getText();
			countryDataMap.put(headerData, colData);
		}
		countryDataMap.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));
	}

	public void clickOnCountry(String country) {
		String rowVal = String.valueOf(getCountryRowCount(country));
		WebElement countryCheckBox = driver.findElements(By.xpath(checkBox.replace("?", rowVal))).get(0);
		JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
		js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", countryCheckBox);
		driver.findElements(By.xpath(checkBox.replace("?", rowVal))).get(0).click();

	}
}
