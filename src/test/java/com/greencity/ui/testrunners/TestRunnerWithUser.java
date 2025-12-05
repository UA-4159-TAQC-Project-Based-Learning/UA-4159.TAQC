package com.greencity.ui.testrunners;

import com.greencity.ui.components.header.core.HeaderComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.profile.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class TestRunnerWithUser extends BaseTestRunner {

    protected ProfilePage profilePage;


    @BeforeClass
    public void loginUser() {

        loginUserWithModal();
//        loginUserWithToken();

    }

    private void setLSItem(String key, String value) {
        threadJs.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", key, value);
    }

    protected void loginUserWithModal() {
        profilePage = homePage
                .getHeader()
                .clickSignIn()
                .typeEmail(testValueProvider.getUserEmail())
                .typePassword(testValueProvider.getUserPassword())
                .submit();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        // Suggest using this instead of Thread.sleep() - some tests need to recreate the Header POM after UI refresh.
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> {
                    WebElement freshHeaderRoot = d.findElement(By.xpath(BasePage.getHEADER_ROOT_LOCATOR_XPATH()));
                    HeaderComponent freshHeader = new HeaderComponent(d, freshHeaderRoot);
                    return freshHeader.isLoggedIn();
                });
    }

    protected void loginUserWithToken() {
        setLSItem("accessToken", testValueProvider.getLsUserAccessToken());
        setLSItem("userId", testValueProvider.getLsUserId());
        setLSItem("refreshToken", testValueProvider.getLsUserRefreshToken());
        setLSItem("name", testValueProvider.getLsUserName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.navigate().refresh();
    }
}
