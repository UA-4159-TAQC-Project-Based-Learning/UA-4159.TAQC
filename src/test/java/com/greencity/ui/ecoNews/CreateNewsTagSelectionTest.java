package com.greencity.ui.ecoNews;

import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsDetailsPage;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static java.lang.Thread.sleep;

public class CreateNewsTagSelectionTest extends TestRunnerWithUser {

    private CreateNewsPage createNewsPage;

    @BeforeMethod
    public void openCreateNewsPage() {
        homePage.refreshPage();
        createNewsPage = homePage.getHeader().clickEcoNewsNavItem().clickCreateNews();
    }

    @Test(description = "User can publish news with 1 tag")
    public void testCreateNewsWithOneTag() {

        SoftAssert softAssert = new SoftAssert();
        String uniqueTitle = "Test One Tag " + System.currentTimeMillis();
        createNewsPage
                .selectTag("News")
                .getTitleInput()
                .typeText(uniqueTitle);

        createNewsPage
                .getContentEditor()
                .typeText("Test content with 20 chars");

        EcoNewsPage ecoNewsPage = createNewsPage
                .getCreateNewsButtonsComponent()
                .clickPublish();

        EcoNewsDetailsPage ecoNewsDetailsPage = ecoNewsPage
                .getOneTableCardByTitle(uniqueTitle)
                .goToDetails();

        softAssert.assertTrue(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("News"),
                "Expected tag 'News' was not found on published news");

        softAssert.assertFalse(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("Events"),
                "Unexpected tag 'Events' was found on published news");

        softAssert.assertFalse(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("Education"),
                "Unexpected tag 'Education' was found on published news");

        softAssert.assertFalse(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("Initiatives"),
                "Unexpected tag 'Initiatives' was found on published news");

        softAssert.assertFalse(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("Ads"),
                "Unexpected tag 'Ads' was found on published news");


        softAssert.assertEquals(ecoNewsDetailsPage.getTitle(), uniqueTitle,
                "Title does not match");

        softAssert.assertAll();
    }

    @Test(description = "User can publish news with 3 tags")
    public void testCreateNewsWithThreeTags() {
        SoftAssert softAssert = new SoftAssert();

        createNewsPage
                .selectTag("News")
                .selectTag("Events")
                .selectTag("Education")
                .getTitleInput()
                .typeText("Test");

        createNewsPage
                .getContentEditor()
                .typeText("Test content with 20 chars");

        EcoNewsPage ecoNewsPage = createNewsPage
                .getCreateNewsButtonsComponent()
                .clickPublish();

        EcoNewsDetailsPage ecoNewsDetailsPage = ecoNewsPage
                .getOneTableCardByTitle("Test")
                .goToDetails();

        softAssert.assertTrue(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("News"),
                "Expected tag 'News' was not found on published news");

        softAssert.assertTrue(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("Events"),
                "Expected tag 'Events' was not found on published news");

        softAssert.assertTrue(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("Education"),
                "Expected tag 'Education' was not found on published news");

        softAssert.assertFalse(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("Initiatives"),
                "Unexpected tag 'Initiatives' was found on published news");

        softAssert.assertFalse(ecoNewsDetailsPage.getNewsTagsInfoComponent().hasTag("Ads"),
                "Unexpected tag 'Ads' was found on published news");


        softAssert.assertEquals(ecoNewsDetailsPage.getTitle(), "Test",
                "Title does not match");

        softAssert.assertAll();
    }

    @Test(description = "Selecting more than three tags is not possible")
    public void testCreateNewsWithMoreThanThreeTags() {
        SoftAssert softAssert = new SoftAssert();

        createNewsPage
                .selectTag("News")
                .selectTag("Events")
                .selectTag("Education")
                .selectTag("Initiatives")
                .selectTag("Ads");


        List<String> selectedTags = createNewsPage
                .getNewsTagsComponent()
                .getSelectedTags();

        softAssert.assertEquals(selectedTags.size(), 3,
                "User should not be able to select more than 3 tags");

        softAssert.assertFalse(selectedTags.contains("Initiatives"),
                "Tag 'Initiatives' should NOT be selectable");

        softAssert.assertFalse(selectedTags.contains("Ads"),
                "Tag 'Ads' should NOT be selectable");

        softAssert.assertAll();

    }

    @Test(description = "Creating news with zero tags selected is not possible")
    public void testCreateNewsWithZeroTags() {

        SoftAssert softAssert = new SoftAssert();

        createNewsPage
                .getTitleInput()
                .typeText("Test");

        createNewsPage
                .getContentEditor()
                .typeText("Test content with 20 chars");

        boolean publishEnabled = createNewsPage
                .getCreateNewsButtonsComponent()
                .isPublishEnabled();

        softAssert.assertFalse(publishEnabled,
                "Expected Publish button to be disabled when tags are not selected");

        softAssert.assertAll();

    }


}
