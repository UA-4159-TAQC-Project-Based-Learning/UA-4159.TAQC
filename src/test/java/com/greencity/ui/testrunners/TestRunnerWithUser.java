package com.greencity.ui.testrunners;

import org.testng.annotations.BeforeClass;

public class TestRunnerWithUser extends BaseTestRunner {



    @BeforeClass
    public void loginUser() {
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

    private void setLSItem(String key, String value) {
        threadJs.executeScript(
                "window.localStorage.setItem(arguments[0], arguments[1]);",
                key, value
        );
    }
}
