package stepdefinitions;

import baseclass.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AmazonHomePage;

public class AmazonSearchStep {

	AmazonHomePage amazonHomePage;

	@Given("I am on amazon app")
	public void i_am_on_amazon_app() {
		amazonHomePage = new AmazonHomePage(Driver.getDriver());
	}

	@When("I search mobile and add to cart")
	public void i_search_mobile_and_add_to_cart() throws InterruptedException {
		amazonHomePage.searchMobileAndAddToCart();
	}

	@Then("I delete product from cart")
	public void i_delete_product_from_cart() {
		amazonHomePage.removeItemFromCart();
	}

}
