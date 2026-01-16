package com.greencity.ui;

import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


@Issue("U4T-11")
public class CreateNewsCheckingDisplayingAllFieldsTest extends TestRunnerWithUser {

    private CreateNewsPage createNewsPage;

    @BeforeMethod
    public void setUp() {

        String ecoNewsUrl = testValueProvider.getBaseUIUrl() + "/news";
        driver.get(ecoNewsUrl);

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        createNewsPage = ecoNewsPage.clickCreateNews();

        createNewsPage.waitForPageToLoad(10);

    }


    @Test(priority = 1, description = "Title block should be displayed")
    public void testTitleBlockDisplayed() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(createNewsPage.getTitleInputRoot()
                        .isDisplayed(),
                "Title block should be displayed");

        softAssert.assertTrue(createNewsPage.getTitleInput()
                        .getLabelElement()
                        .isDisplayed(),
                "Title label should be displayed");

        softAssert.assertTrue(createNewsPage.getTitleInput()
                        .getFieldInfoElement()
                        .isDisplayed(),
                "Title field info should be displayed");

        softAssert.assertTrue(createNewsPage.getTitleInput()
                        .getFieldElement()
                        .isDisplayed(),
                "Title input field should be displayed");

        softAssert.assertAll();
    }

    @Test (priority = 2, description = "Tags block should be displayed")
    public void testTagsBlockDisplayed(){
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(createNewsPage.getNewsTagsComponent()
                        .getRootElement()
                        .isDisplayed(),
                "Tags should be displayed");

        createNewsPage.getNewsTagsComponent()
                .selectTag("News");
        softAssert.assertTrue(createNewsPage.getNewsTagsComponent()
                .isTagSelected("News"),
                "Tag 'News' should be selected");

        createNewsPage.getNewsTagsComponent()
                .unselectTag("News");
        softAssert.assertFalse(createNewsPage.getNewsTagsComponent()
                .isTagSelected("News"),
                "Tag 'News' should be unselected");

        softAssert.assertAll();
    }

    @Test (priority = 3, description = "Image Add block should be displayed")
    public void testImageBlockDisplayed() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(createNewsPage.getAddImageComponent()
                        .getRootElement()
                        .isDisplayed(),
                "Add image block should be displayed");

        softAssert.assertTrue(createNewsPage.getAddImageComponent()
                        .getImageContainer().isDisplayed(),
                "Add image dropzone should be displayed");

        softAssert.assertAll();
    }

    @Test (priority = 4, description = "Content Editor should be displayed")
    public void testContentEditorDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createNewsPage.getContentEditor()
                        .getTextInputArea()
                        .isDisplayed(),
                "Content editor should be displayed");

        softAssert.assertTrue(createNewsPage.getContentEditor()
                        .getFieldInfoElement()
                        .getText().contains("63 206"),
                "Field info of main text should contain '63 206'");

        softAssert.assertAll();
    }

    @Test (priority = 5, description = "Author of news should be filled")
    public void testAuthorIsFilled(){
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertFalse(createNewsPage.getAuthorOfNews().
                        getText().isEmpty(),
                "Author should be not empty");

        softAssert.assertEquals(createNewsPage.getAuthorOfNews().
                        getText().trim(),
                testValueProvider.getLsUserName().trim(),
                "Author should be equal to Username");

        softAssert.assertFalse(Boolean.parseBoolean(createNewsPage.getAuthorOfNews()
                .getAttribute("contenteditable")), "Author field should not be editable");

        softAssert.assertAll();
    }

    @Test (priority = 6, description = "Date of news should be filled")
    public void testDateIsDisplayed(){
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(createNewsPage.getActualDate().isDisplayed(),
                "Date should be displayed");

        softAssert.assertFalse(createNewsPage.getActualDate()
                .getText().trim().isEmpty(), "Date should not be empty");

        softAssert.assertFalse(Boolean.parseBoolean(createNewsPage.getActualDate()
                .getAttribute("contenteditable")), "Date field should not be editable");

        boolean isValidFormat =
                createNewsPage.getActualDate()
                        .getText()
                        .trim()
                        .matches("^([A-Z][a-z]{2,}) ([1-9]|[12][0-9]|3[01]), \\d{4}$")
                || createNewsPage.getActualDate()
                        .getText()
                        .trim()
                        .matches("^[а-яіїєґ]{4}\\.\\s\\d{1,2},\\s\\d{4}\\sр\\.$");

        softAssert.assertTrue(isValidFormat,
                "Date format should match 'MMMM d, yyyy'");


        softAssert.assertAll();
    }

    @Test (priority = 7, description = "Source block should be displayed")
    public void testSourceBlockDisplayed(){
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(createNewsPage.getSourceInputRoot()
                        .isDisplayed(),
                "Source block should be displayed");

        softAssert.assertTrue(createNewsPage.getSourceInput()
                        .getLabelElement()
                        .isDisplayed(),
                "Source label should be displayed");

        softAssert.assertTrue(createNewsPage.getSourceInput()
                        .getFieldInfoElement()
                        .isDisplayed(),
                "Source field info should be displayed");

        softAssert.assertTrue(createNewsPage.getSourceInput()
                        .getFieldElement()
                        .isDisplayed(),
                "Source input field should be displayed");

        softAssert.assertTrue(createNewsPage.getSourceInput()
                .getFieldInfoElement()
                .getText().trim()
                .contains("http(s)://"));

        softAssert.assertAll();
    }


    @Test (priority = 8, description = "'Cancel', 'Preview' and 'Publish' buttons should be displayed")
    public void testCancelPreviewSubmitButtonsDisplayed(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createNewsPage.getCreateNewsButtonsComponent()
                        .getRootElement()
                        .isDisplayed(),
                "Buttons 'Cancel', 'Preview', 'Publish' block should be displayed");

        softAssert.assertTrue(createNewsPage.getCreateNewsButtonsComponent()
                        .getCancelButton()
                        .isDisplayed(),
                "Button 'Cancel' should be displayed");

        softAssert.assertTrue(createNewsPage.getCreateNewsButtonsComponent()
                        .getPreviewButton()
                        .isDisplayed(),
                "Button 'Preview' should be displayed");

        softAssert.assertTrue(createNewsPage.getCreateNewsButtonsComponent()
                        .getPublishButton()
                        .isDisplayed(),
                "Button 'Publish' should be displayed");

        softAssert.assertAll();
    }





}
