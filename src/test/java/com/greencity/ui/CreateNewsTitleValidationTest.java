package com.greencity.ui;

import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewsTitleValidationTest extends TestRunnerWithUser {

    private CreateNewsPage createNewsPage;

    @BeforeMethod
    public void setUp() {

        initDriver();

        String createNewsUrl = testValueProvider.getBaseUIUrl() + "/news/create-news";
        driver.get(createNewsUrl);

        createNewsPage = new CreateNewsPage(driver);
        createNewsPage.waitForPageToLoad(10);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test(description = "Title input should be invalid when empty")
    public void testTitleEmpty() {
        Assert.assertTrue(
                createNewsPage.getTitleInput()
                        .typeText("")
                        .clear()
                        .isInvalid(),
                "Expected title input has ng-invalid class tag"
        );
    }

    @Test(description = "Title input should not exceed 170 characters")
    public void testTitleMaxLength() {
        String longTitle = "A".repeat(200);

        String actualValue = createNewsPage
                .getTitleInput()
                .typeText(longTitle)
                .getValue();

        Assert.assertTrue(actualValue.length() <= 172,
                "Expected title length <= 170, but got: " + actualValue.length());
    }
}

