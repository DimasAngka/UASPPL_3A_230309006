package com.praktikum.testing.otomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(id = "cartur")
    private WebElement cartLink;

    @FindBy(linkText = "Log in")
    private WebElement loginLink;

    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogin() {
        click(loginLink);
    }

    public void clickCart() {
        click(cartLink);
    }

    public boolean isWelcomeMessageDisplayed(String username) {
        return isDisplayed(welcomeMessage) && welcomeMessage.getText().contains(username);
    }

    public void open() {
        driver.get("https://www.demoblaze.com/");
        wait.until(ExpectedConditions.titleContains("STORE"));
    }
}