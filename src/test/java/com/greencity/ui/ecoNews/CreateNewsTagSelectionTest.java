package com.greencity.ui.ecoNews;

import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsDetailsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateNewsTagSelectionTest extends TestRunnerWithUser {

    private CreateNewsPage createNewsPage;

    @BeforeMethod
    public void openCreateNewsPage() {
        createNewsPage = new CreateNewsPage(driver);
        createNewsPage.waitForPageToLoad(10);
    }

    @Test(description = "User can publish news with 1 tag")
    public void testCreateNewsWithOneTag() {

        SoftAssert softAssert = new SoftAssert();

        EcoNewsDetailsPage ecoNewsDetailsPage = createNewsPage
                .selectTag("News")
                .getTitleInput()
                .typeText("Test")
                .getContentEditor()
                .typeText("Test content with 20 chars")
                .getCreateNewsButtonsComponent()
                .clickPublish();

        softAssert.assertTrue(ecoNewsDetailsPage.getTagsRoot().hasTag("News"),
                "Expected tag 'News' was not found on published news");

        softAssert.assertEquals(ecoNewsDetailsPage.getTitle(), "Test",
                "Title does not match");

        softAssert.assertAll();
    }

    @Test(description = "User can publish news with 3 tags")
    public void testCreateNewsWithThreeTags() {
        SoftAssert softAssert = new SoftAssert();

        EcoNewsDetailsPage ecoNewsDetailsPage = createNewsPage
                .selectTag("News")
                .selectTag("Events")
                .selectTag("Education")
                .getTitleInput()
                .typeText("Test")
                .getContentEditor()
                .typeText("Test content with 20 chars")
                .getCreateNewsButtonsComponent()
                .clickPublish();

        softAssert.assertTrue(ecoNewsDetailsPage.getTagsRoot().hasTag("News"),
                "Expected tag 'News' was not found on published news");

        softAssert.assertTrue(ecoNewsDetailsPage.getTagsRoot().hasTag("Events"),
                "Expected tag 'Events' was not found on published news");

        softAssert.assertTrue(ecoNewsDetailsPage.getTagsRoot().hasTag("Education"),
                "Expected tag 'Education' was not found on published news");

        softAssert.assertEquals(ecoNewsDetailsPage.getTitle(), "Test",
                "Title does not match");

        softAssert.assertAll();
    }

    @Test(description = "Selecting more than three tags is not possible")
    public void testCreateNewsWithMoreThanThreeTags() {

    }

    @Test(description = "Creating news with zero tags selected is not possible")
    public void testCreateNewsWithZeroTags() {

        SoftAssert softAssert = new SoftAssert();

        boolean publishEnabled = createNewsPage
                .getTitleInput()
                .typeText("Test")
                .getContentEditor()
                .typeText("Test content with 20 chars")
                .getCreateNewsButtonsComponent()
                .isPublishEnabled();
        softAssert.assertFalse(publishEnabled,
                "Expected Publish button to be disabled when title is empty");

        softAssert.assertAll();

    }


}
