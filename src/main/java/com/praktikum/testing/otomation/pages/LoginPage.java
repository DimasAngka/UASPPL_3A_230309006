package com.praktikum.testing.otomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;

    @FindBy(className = "sweet-alert")
    private WebElement alertBox;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        enterText(usernameField, username);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
        // Tunggu alert muncul/keluar
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(alertBox),
                    ExpectedConditions.invisibilityOfElementLocated((By) alertBox)
            ));
        } catch (Exception ignored) {}
    }

    public boolean isErrorMessageDisplayed(String expected) {
        try {
            WebElement text = driver.findElement(org.openqa.selenium.By.className("text"));
            return text.isDisplayed() && text.getText().contains(expected);
        } catch (Exception e) {
            return false;
        }
    }
}