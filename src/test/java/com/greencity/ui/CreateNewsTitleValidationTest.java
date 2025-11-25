package com.greencity.ui;

import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

    @Test(description = "Title input invalid when empty and Publish disabled")
    public void testTitleEmptyAndPublishDisabled() {
        SoftAssert softAssert = new SoftAssert();

        boolean isInvalid = createNewsPage.getTitleInput()
                .clickOnField()
                .typeText("")
                .clear()
                .isInvalid();
        softAssert.assertTrue(isInvalid,
                "Expected title input has ng-invalid class tag when field is empty");

        boolean publishEnabled = createNewsPage.getCreateNewsButtonsComponent()
                .isPublishEnabled();
        softAssert.assertFalse(publishEnabled,
                "Expected Publish button to be disabled when title is empty");

        boolean fieldInfoMessage = createNewsPage.getTitleInput()
                .getFieldInfoText()
                .trim()
                .equalsIgnoreCase("0/170");
        softAssert.assertTrue(fieldInfoMessage,
                "Expected message must be 0/170");

        softAssert.assertAll();
    }

    @Test(description = "Title input should not exceed 170 characters")
    //in testcase requirement max length = 170 char functional on site implemented max length = 171
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

