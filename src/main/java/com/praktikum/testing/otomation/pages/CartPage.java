package com.praktikum.testing.otomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(xpath = "//td[text()='Samsung galaxy s6']")
    private WebElement samsungInCart;

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSamsungGalaxyS6Present() {
        return isDisplayed(samsungInCart);
    }

    public void clickPlaceOrder() {
        click(placeOrderBtn);
    }
}