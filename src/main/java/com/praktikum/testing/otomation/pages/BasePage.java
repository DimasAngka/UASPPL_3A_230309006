package com.praktikum.testing.otomation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected FluentWait<WebDriver> wait; // <-- Ganti dari WebDriverWait ke FluentWait

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Gunakan FluentWait untuk stability
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        PageFactory.initElements(driver, this);
    }

    protected void waitForVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void enterText(WebElement element, String text) {
        waitForVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(WebElement element) {
        waitForClickable(element);
        element.click();
        acceptAlertIfPresent(); // Auto-handle JS alert
    }

    protected String getText(WebElement element) {
        waitForVisible(element);
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            waitForVisible(element);
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    // Method khusus untuk handle JS alert (critical untuk Demoblaze)
    protected void acceptAlertIfPresent() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            if (localWait.until(ExpectedConditions.alertIsPresent()) != null) {
                driver.switchTo().alert().accept();
            }
        } catch (TimeoutException e) {
            // Tidak ada alert — boleh lanjut
        }
    }

    // Method untuk menunggu halaman selesai load (penting setelah redirect)
    protected void waitForPageLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
}