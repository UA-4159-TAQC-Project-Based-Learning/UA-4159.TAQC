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
/*
        SoftAssert softAssert = new SoftAssert();

        EcoNewsDetailsPage ecoNewsDetailsPage = createNewsPage
                .getNewsTagsComponent()
                .selectTag("News")
                .getTitleInput()
                .typeText("Test")
                .getContentEditor()
                .typeText("Test content with 20 chars")
                .clickPublish();

        softAssert.assertTrue(ecoNewsDetailsPage.isOpened(),
                "Eco News details page did not open after publishing");

        softAssert.assertTrue(ecoNewsDetailsPage.getTags().contains("News"),
                "Expected tag 'News' was not found on published news");

        softAssert.assertEquals(ecoNewsDetailsPage.getTitle(), "Test",
                "Title does not match");

        softAssert.assertAll();

 */
    }

    @Test(description = "User can publish news with 3 tags")
    public void testCreateNewsWithThreeTags() {

    }

    @Test(description = "Selecting more than three tags is not possible")
    public void testCreateNewsWithMoreThanThreeTags() {

    }

    @Test(description = "Creating news with zero tags selected is not possible")
    public void testCreateNewsWithZeroTags() {

    }


}
