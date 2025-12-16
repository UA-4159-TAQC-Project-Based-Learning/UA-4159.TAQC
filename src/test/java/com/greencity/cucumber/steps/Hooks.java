package com.greencity.cucumber.steps;

import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.utils.TestValueProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Hooks {

    @Getter
    private final TestValueProvider testValueProvider = new TestValueProvider();

    @Getter
    private WebDriver driver;

    @Getter
    private SoftAssert softAssert;

    @Step("init ChromeDriver")
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");//Disables notifications
        options.addArguments("--disable-popup-blocking");//Disables popup blocking

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));
    }


    @Before
    public void beforeScenario() {
        if (driver == null) {
            initDriver();
        }
        softAssert = new SoftAssert();
        driver.get(testValueProvider.getBaseUIUrl());
        new HomePage(driver).waitUntilPageLoaded();
    }

    @After
    public void afterScenario() {
        softAssert.assertAll();
        if (driver != null) {
            driver.quit();
        }
    }
}
