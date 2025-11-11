package com.greencity.cucumber.steps;

import com.greencity.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class BaseStep {

    protected static WebDriver driver;
    protected static List<String> createdPartners = new ArrayList<>();
    protected TestValueProvider provider = new TestValueProvider();

    @Step("init ChromeDriver")
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(provider.getImplicitlyWait()));
    }

    protected void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    @Step("set AccessToken")
//    public void setAccessToken() {
//        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
//        LocalStorage localStorage = webStorage.getLocalStorage();
//        localStorage.setItem("AccessToken", provider.getAccessToken());
//        localStorage.setItem("RefreshToken", provider.getRefreshToken());
//    }


}