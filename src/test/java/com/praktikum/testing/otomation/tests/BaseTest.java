package com.praktikum.testing.otomation.tests;

import com.praktikum.testing.otomation.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;

    private static List<String> passed = new ArrayList<>();
    private static List<String> failed = new ArrayList<>();

    @BeforeSuite
    public void beforeSuite() {
        passed.clear();
        failed.clear();
    }

    @BeforeMethod
    public void setUp(Method method) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().clearDriverCache().setup();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        homePage.open();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (result.isSuccess()) {
            passed.add(testName);
        } else {
            failed.add(testName + " → " + (result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error"));
        }

        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {}
        }
    }

    @AfterSuite
    public void afterSuite() {
        // ✅ Paksa buat folder dan file
        File outputDir = new File("target/test-output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File reportFile = new File(outputDir, "Report.html");

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><meta charset='UTF-8'>");
        html.append("<title>UAS PPL Report</title>");
        html.append("<style>body{font-family:Arial,sans-serif;margin:40px;background:#f5f5f5}.container{max-width:900px;margin:auto;background:white;padding:30px;border-radius:10px;box-shadow:0 0 10px rgba(0,0,0,0.1)}h1{color:#2c3e50;text-align:center}.summary{background:#ecf0f1;padding:20px;border-radius:8px;margin-bottom:25px}.passed{color:green;font-weight:bold}.failed{color:red;font-weight:bold}ul{padding-left:20px}li{margin:8px 0}</style></head><body>");
        html.append("<div class='container'>");
        html.append("<h1>✅ UAS PPL - Test Automation Report</h1>");
        html.append("<div class='summary'>");
        html.append("<p><strong>Total Test:</strong> ").append(passed.size() + failed.size()).append("</p>");
        html.append("<p><strong>Status:</strong> ");
        if (failed.isEmpty()) {
            html.append("<span class='passed'>SEMUA BERHASIL ✅ (").append(passed.size()).append(")</span>");
        } else {
            html.append("<span class='failed'>").append(failed.size()).append(" GAGAL</span>, <span class='passed'>").append(passed.size()).append(" BERHASIL</span>");
        }
        html.append("</p></div>");

        if (!passed.isEmpty()) {
            html.append("<h3>✅ Test Berhasil (").append(passed.size()).append(")</h3>");
            html.append("<ul>");
            for (String p : passed) {
                html.append("<li>").append(p).append("</li>");
            }
            html.append("</ul>");
        }

        if (!failed.isEmpty()) {
            html.append("<h3>❌ Test Gagal (").append(failed.size()).append(")</h3>");
            html.append("<ul>");
            for (String f : failed) {
                html.append("<li>").append(f).append("</li>");
            }
            html.append("</ul>");
        }

        html.append("<hr><p><em>Laporan otomatis oleh Selenium + TestNG | NPM: 230309006</em></p>");
        html.append("</div></body></html>");

        try {
            FileWriter fw = new FileWriter(reportFile);
            fw.write(html.toString());
            fw.close();
            System.out.println("✅ Report berhasil dibuat: " + reportFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Gagal membuat report: " + e.getMessage());
            e.printStackTrace();
        }
    }
}