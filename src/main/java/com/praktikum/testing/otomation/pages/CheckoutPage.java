package com.praktikum.testing.otomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseBtn;

    @FindBy(className = "sweet-alert")
    private WebElement thankYouModal;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) { enterText(nameField, name); }
    public void enterCountry(String country) { enterText(countryField, country); }
    public void enterCity(String city) { enterText(cityField, city); }
    public void enterCard(String card) { enterText(cardField, card); }
    public void enterMonth(String month) { enterText(monthField, month); }
    public void enterYear(String year) { enterText(yearField, year); }

    public void clickPurchase() {
        click(purchaseBtn);
        wait.until(ExpectedConditions.visibilityOf(thankYouModal));
    }

    public boolean isThankYouMessageVisible() {
        return isDisplayed(thankYouModal);
    }

    public void clickOK() {
        driver.findElement(org.openqa.selenium.By.xpath("//button[text()='OK']")).click();
    }
}