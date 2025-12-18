package com.greencity.ui.ecoNews;

import com.greencity.ui.components.EcoNewsListCardComponent;
import com.greencity.ui.pages.*;
import com.greencity.ui.pages.ecoNewsPreviewPage.PreviewEcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


@Epic("Eco News")
@Feature("Eco News Preview")
public class EcoNewsPreviewPageTest extends TestRunnerWithUser {


    private SoftAssert softAssert;
    private String testTitle = "test title 1234567890";
    private String testContent = "test content 1234567890";
    private static final String DATE_PATTERN_UKR =
            "^(0?[1-9]|[12][0-9]|3[01])\\s" +
                    "(січ\\.|лют\\.|бер\\.|квіт\\.|трав\\.|черв\\.|лип\\.|серп\\.|вер\\.|жовт\\.|лист\\.|груд\\.)\\s" +
                    "\\d{4}\\sр\\.$";

    private static final String DATE_PATTERN_ENG =
            "^(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s(0?[1-9]|[12][0-9]|3[01]),\\s\\d{4}$";



    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();
        LocalTime localTime = LocalTime.now(); // Get current time for validation
        testTitle = "test title " + localTime;
        testContent = "test content " + localTime;
    }

    @Issue("U4T-21")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create Eco News, verify preview is opened. Verifying title, author and date.")
    @Test(description = "Basic Preview Functionality")
    public void previewOpensSuccessfully() {
        EditEcoNewsPage editNewsPage = new EditEcoNewsPage(driver);
        editNewsPage.getHeader().getNavigation().clickEcoNews();
        EcoNewsPage newsPage = new EcoNewsPage(driver);
        newsPage.clickCreateNews();


        CreateNewsPage createNewsPage = new CreateNewsPage(driver);
        createNewsPage.getTitleInput().typeText(testTitle);
        createNewsPage.enterTextIntoQLEditor(testContent);
        createNewsPage.getCreateNewsButtonsComponent().getPreviewButton().click();

        CreateNewsPreviewPage previewPage = new CreateNewsPreviewPage(driver);

        softAssert.assertTrue(previewPage.getNewsTitle().isDisplayed(), "NewsTitle is not displayed.");
        softAssert.assertEquals(previewPage.getNewsTitle().getText(),testTitle,  "NewsTitle is wrong.");

        softAssert.assertTrue(previewPage.getAuthor().isDisplayed(), "Author is not displayed.");
        softAssert.assertTrue(previewPage.getAuthor().getText().contains(testValueProvider.getLsUserName()),  "Author is wrong.");

        softAssert.assertTrue(previewPage.getTextContent().isDisplayed(), "Content is not displayed.");
        softAssert.assertEquals(previewPage.getTextContent().getText(), testContent,  "Content is wrong.");

        LocalDate currentDate = LocalDate.now(); // Get current date for validation
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        String formatted = currentDate.format(formatter);


        softAssert.assertTrue(previewPage.getDate().isDisplayed(), "Date is not displayed.");

        String date =  previewPage.getDate().getText();
        boolean isEngFormatValid = Pattern.matches(DATE_PATTERN_ENG, date);
        boolean isUkrFormatValid = Pattern.matches(DATE_PATTERN_UKR, date);

        softAssert.assertTrue(isEngFormatValid || isUkrFormatValid,  "Date is wrong.");

        softAssert.assertTrue(previewPage.getBackLink().isDisplayed(), "BackLink is not displayed.");

        softAssert.assertAll();
    }

    @Issue("U4T-3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Eco News: Verifying that publish from preview is working.")
    @Test(description = "Preview Functionality")
    public void publishNewsFromPreview() {
        EditEcoNewsPage editNewsPage = new EditEcoNewsPage(driver);
        editNewsPage.getHeader().getNavigation().clickEcoNews();

        EcoNewsPage newsPage = new EcoNewsPage(driver);
        newsPage.clickCreateNews();

        CreateNewsPage createNewsPage = new CreateNewsPage(driver);
        createNewsPage.getTitleInput().typeText(testTitle);
        createNewsPage.enterTextIntoQLEditor(testContent);
        createNewsPage.selectTag("News");

        createNewsPage.getCreateNewsButtonsComponent().getPreviewButton().click();

        CreateNewsPreviewPage previewPage = new CreateNewsPreviewPage(driver);

        softAssert.assertTrue(previewPage.isPublishButtonPresent(), "Publish button is not present.");
        softAssert.assertTrue(previewPage.getPublishButton().isDisplayed(), "Publish button is not displayed.");

        previewPage.getPublishButton().click();

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        softAssert.assertTrue(ecoNewsPage.ecoNewsPageIsOpened(), "Eco news page is not opened.");

        ecoNewsPage.switchNewsPageToListView();
        softAssert.assertNotNull(ecoNewsPage.findCardByTitle(testTitle), "News is not published from preview.");

        softAssert.assertAll();
    }
}
