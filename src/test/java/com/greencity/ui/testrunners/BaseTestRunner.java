package com.greencity.ui.testrunners;

import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;


public class BaseTestRunner {
    protected static TestValueProvider testValueProvider;
    protected WebDriver driver;
    protected HomePage homePage;
    protected JavascriptExecutor threadJs;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        testValueProvider = new TestValueProvider();
    }

    @Step("init ChromeDriver")
    public void initDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");//Disables notifications
        options.addArguments("--disable-popup-blocking");//Disables popup blocking
//        options.addArguments("--headless");// Runs Chrome in headless mode (without GUI)

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));
        threadJs = (JavascriptExecutor) driver;
    }

    @BeforeClass
    public void beforeClass() {
        if (driver == null) {
            initDriver();
        }
        driver.get(testValueProvider.getBaseUIUrl());
        homePage = new HomePage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("driver = " + driver);
        if (driver != null) {
            driver.quit();
        }
    }
}