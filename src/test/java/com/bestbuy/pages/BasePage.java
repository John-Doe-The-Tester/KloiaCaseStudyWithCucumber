package com.bestbuy.pages;

import com.bestbuy.stepdefs.Hooks;
import com.bestbuy.utils.Driver;
import com.bestbuy.utils.PropertyReader;
import com.bestbuy.utils.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {

	protected WebDriver driver;

	//to initialize @FindBy annotations with PageFactory class
	BasePage() {
		this.driver = Hooks.driver;
		PageFactory.initElements(driver, this);
	}

	//--search box--
	@FindBy(id = "gh-search-input")
	private WebElement searchBox;

	//--find button--
	@FindBy(className = "header-search-button")
	private WebElement findBtn;

	//--See all your saved items btn --
	@FindBy(linkText = "See all your saved items ›")
	private WebElement seeAllSavedItems;

	//*****************************************

	//search for item
	public void searchItem(String itemName){
		UtilityMethods.waitClickability(searchBox,3);
		searchBox.sendKeys(itemName);
		findBtn.click();
	}

	//navigate through menu options
	public void navigateToMenuOptions(String menuName){
		String locator = "//*[text()='" + menuName + "']";
		UtilityMethods.waitClickability(By.xpath(locator),5);
		WebElement menu = driver.findElement(By.xpath("//*[text()='" + menuName + "']"));
		menu.click();
	}

	//navigate through menu options
	public void navigateToMenuOptions(List<String> modules){
		for (int i = 0; i < modules.size(); i++) {
			String locator = "//*[text()='" + modules.get(i) + "']";
			UtilityMethods.waitClickability(By.xpath(locator),5);
			WebElement menu = driver.findElement(By.xpath("//*[text()='" + modules.get(i) + "']"));
			menu.click();
		}
	}

	//navigate through dropdown menus at the right top of the page
	public void navigateToRightTopMenus(String menuName) {
		String locator = "//button//*[text()='" + menuName + "']";
		UtilityMethods.waitClickability(By.xpath(locator), 5);
		WebElement menu = driver.findElement(By.xpath(locator));
		menu.click();
	}

	//go to all saved items
	public void goToAllSavedItems(){
		UtilityMethods.waitClickability(seeAllSavedItems, 3);
		seeAllSavedItems.click();
	}

}