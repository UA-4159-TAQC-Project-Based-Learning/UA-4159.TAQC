package com.greencity.ui;

import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class CreateNewsTitleValidationTest extends TestRunnerWithUser {

    private CreateNewsPage createNewsPage;

    @BeforeMethod
    public void setUp() {

        String createNewsUrl = testValueProvider.getBaseUIUrl() + "/news/create-news";
        driver.get(createNewsUrl);

        createNewsPage = new CreateNewsPage(driver);
        createNewsPage.waitForPageToLoad(10);
    }



    @Test(description = "Title input invalid when empty and Publish disabled")
    public void testTitleEmptyAndPublishDisabled() {
        SoftAssert softAssert = new SoftAssert();

        boolean isInvalid = createNewsPage.getTitleInput()
                .clickOnField()
                .typeText("")
                .clear()
                .isInvalidField();
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
        SoftAssert softAssert = new SoftAssert();

        String longTitle = "A".repeat(171);
        String actualValue = createNewsPage
                .getTitleInput()
                .clickOnField()
                .typeText(longTitle)
                .getValue();
        softAssert.assertTrue(actualValue.length() <= 170,
                "Expected title length <= 170, but got: " + actualValue.length());

        boolean warningTag = createNewsPage.getTitleInput()
                        .hasWarningFieldInfo();
        softAssert.assertTrue(warningTag,
                "Expected field info has warning when length > 170");


        softAssert.assertAll();
    }

    @Test(description = "Valid title should have correct counter value")
    public void testTitleValidValue(){
        SoftAssert softAssert = new SoftAssert();

        String validTitle = "Test News";
        createNewsPage.getTitleInput()
                .clickOnField()
                .typeText(validTitle);
        String actualCounterValue = createNewsPage.getTitleInput()
                        .getFieldInfoText();
        softAssert.assertEquals(actualCounterValue, validTitle.length() + "/170",
                "Expected title length " + validTitle.length() + "/170 " +
                "but actual is " + actualCounterValue);

        boolean warningTag = createNewsPage.getTitleInput()
                .hasWarningFieldInfo();
        softAssert.assertFalse(warningTag,
                "Expected no warning in fieldInfo when title length " + validTitle.length() + "/170 ");

        boolean actualPublishEnabled = createNewsPage.getCreateNewsButtonsComponent()
                .isPublishEnabled();
        softAssert.assertFalse(actualPublishEnabled,
                "Publish button should be not enable before entering main text and selecting news tag");

        softAssert.assertAll();
    }

}

