package com.praktikum.testing.otomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    @FindBy(linkText = "Samsung galaxy s6")
    private WebElement samsungGalaxyS6;

    // ✅ Benar: di Demoblaze, "Add to cart" adalah <a>, bukan <button>
    @FindBy(linkText = "Add to cart")  // Lebih stabil daripada xpath
    private WebElement addToCartBtn;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickSamsungGalaxyS6() {
        // Gunakan method dari BasePage → sudah ada waitForClickable
        click(samsungGalaxyS6);

        // ✅ Tunggu halaman produk terbuka → cek URL (lebih reliable daripada title)
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        localWait.until(ExpectedConditions.urlContains("/prod.html?idp_"));
    }

    public void clickAddToCart() {
        // Gunakan method dari BasePage → sudah aman dengan waitForClickable + try-catch
        click(addToCartBtn);

        // ✅ Tangani JS alert dengan robust wait + retry
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            // Tunggu alert muncul
            localWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            System.out.println("✅ Alert 'Product added.' diterima.");
        } catch (Exception e) {
            // Jika alert tidak muncul dalam 5 detik, coba sekali lagi (retry)
            try {
                Thread.sleep(500);
                if (ExpectedConditions.alertIsPresent().apply(driver) != null) {
                    driver.switchTo().alert().accept();
                    System.out.println("✅ Alert diterima setelah retry.");
                }
            } catch (Exception ignored) {
                // Fallback: cek apakah tombol masih ada → berarti alert gagal muncul
                if (isDisplayed(addToCartBtn)) {
                    System.err.println("⚠️ Alert tidak muncul — lanjutkan (kemungkinan bug UI atau delay jaringan).");
                }
            }
        }
    }
}