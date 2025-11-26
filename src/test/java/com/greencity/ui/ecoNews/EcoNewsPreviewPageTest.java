package com.greencity.ui.ecoNews;

import com.greencity.ui.components.buttons.EditNewsButtonsComponent;
import com.greencity.ui.pages.*;
import com.greencity.ui.pages.ecoNewsPreviewPage.PreviewEcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertTrue;


//@Test(enabled = false)
public class EcoNewsPreviewPageTest extends TestRunnerWithUser {

    private EditEcoNewsPage editEcoNewsPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void beforeMethod() {
        loginUser();
        softAssert = new SoftAssert();
    }

    @Test
    public void previewOpensSuccessfully() throws InterruptedException {
        EditEcoNewsPage editNewsPage = new EditEcoNewsPage(driver);
        editNewsPage.getHeader().getNavigation().getEcoNewsLink().click();
        editNewsPage.waitForPageToLoad(10000);

        EcoNewsPage newsPage = new EcoNewsPage(driver);
        newsPage.clickCreateNews();
        editNewsPage.waitForPageToLoad(10000);

        String title = "test title 1234567890";
        String content = "test content 1234567890";

        CreateNewsPage createNewsPage = new CreateNewsPage(driver);
        createNewsPage.getTitleInput().typeText(title);
        WebElement qlEditor =createNewsPage.getQlEditor();
        qlEditor.click();
        qlEditor.sendKeys(content);
        createNewsPage.getCreateNewsButtonsComponent().getPreviewButton().click();
        editNewsPage.waitForPageToLoad(10000);

        CreateNewsPreviewPage previewPage = new CreateNewsPreviewPage(driver);

        softAssert.assertTrue(previewPage.getNewsTitle().isDisplayed(), "NewsTitle is not displayed.");
        softAssert.assertEquals(previewPage.getNewsTitle().getText(),title,  "NewsTitle is wrong.");

        softAssert.assertTrue(previewPage.getAuthor().isDisplayed(), "Author is not displayed.");
        softAssert.assertTrue(previewPage.getAuthor().getText().contains(testValueProvider.getLsUserName()),  "Author is wrong.");

        softAssert.assertTrue(previewPage.getTextContent().isDisplayed(), "Content is not displayed.");
        softAssert.assertEquals(previewPage.getTextContent().getText(), content,  "Content is wrong.");

        LocalDate currentDate = LocalDate.now(); // or any date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formatted = currentDate.format(formatter);

        softAssert.assertTrue(previewPage.getDate().isDisplayed(), "Date is not displayed.");
        softAssert.assertEquals(previewPage.getDate().getText(), formatted,  "Date is wrong.");

        softAssert.assertTrue(previewPage.getBackLink().isDisplayed(), "BackLink is not displayed.");

        softAssert.assertAll();
    }

}
