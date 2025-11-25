package com.greencity.ui.testrunners;

import com.greencity.ui.pages.profile.ProfilePage;
import org.testng.annotations.BeforeClass;

public class TestRunnerWithUser extends BaseTestRunner {

    protected ProfilePage profilePage;


    @BeforeClass
    public void loginUser() {

        loginUserWithModal();

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
