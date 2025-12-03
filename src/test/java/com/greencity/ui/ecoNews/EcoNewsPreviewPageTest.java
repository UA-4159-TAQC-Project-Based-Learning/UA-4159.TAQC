package com.greencity.ui.ecoNews;

import com.greencity.ui.pages.*;
import com.greencity.ui.pages.ecoNewsPreviewPage.PreviewEcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class EcoNewsPreviewPageTest extends TestRunnerWithUser {


    private SoftAssert softAssert;

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();
    }

    @Test
    public void previewOpensSuccessfully() {
        EditEcoNewsPage editNewsPage = new EditEcoNewsPage(driver);
        editNewsPage.getHeader().getNavigation().getEcoNewsLink().click();
        editNewsPage.waitForPageToLoad(10000);

        EcoNewsPage newsPage = new EcoNewsPage(driver);
        newsPage.clickCreateNews();
        newsPage.waitForPageToLoad(10000);

        String title = "test title 1234567890";
        String content = "test content 1234567890";

        CreateNewsPage createNewsPage = new CreateNewsPage(driver);
        createNewsPage.getTitleInput().typeText(title);
        WebElement qlEditor = createNewsPage.getQlEditor();
        qlEditor.click();
        qlEditor.sendKeys(content);
        createNewsPage.getCreateNewsButtonsComponent().getPreviewButton().click();
        createNewsPage.waitForPageToLoad(10000);

        CreateNewsPreviewPage previewPage = new CreateNewsPreviewPage(driver);

        softAssert.assertTrue(previewPage.getNewsTitle().isDisplayed(), "NewsTitle is not displayed.");
        softAssert.assertEquals(previewPage.getNewsTitle().getText(),title,  "NewsTitle is wrong.");

        softAssert.assertTrue(previewPage.getAuthor().isDisplayed(), "Author is not displayed.");
        softAssert.assertTrue(previewPage.getAuthor().getText().contains(testValueProvider.getLsUserName()),  "Author is wrong.");

        softAssert.assertTrue(previewPage.getTextContent().isDisplayed(), "Content is not displayed.");
        softAssert.assertEquals(previewPage.getTextContent().getText(), content,  "Content is wrong.");

        LocalDate currentDate = LocalDate.now(); // Get current date for validation
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        String formatted = currentDate.format(formatter);

        softAssert.assertTrue(previewPage.getDate().isDisplayed(), "Date is not displayed.");
        softAssert.assertEquals(previewPage.getDate().getText(), formatted,  "Date is wrong.");

        softAssert.assertTrue(previewPage.getBackLink().isDisplayed(), "BackLink is not displayed.");

        softAssert.assertAll();
    }

}
