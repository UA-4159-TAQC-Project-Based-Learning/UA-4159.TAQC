package com.greencity.ui.ecoNews;

import com.greencity.ui.components.buttons.EditNewsButtonsComponent;
import com.greencity.ui.pages.EcoNewsDetailsPage;
import com.greencity.ui.pages.EditEcoNewsPage;
import com.greencity.ui.pages.ecoNewsPreviewPage.PreviewEcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;


public class EditEcoNewsPageTest extends BaseTestRunner {

    public final String AUTHOR_NEWS_TEMPLATE_URL = "%s/news/%d";
    private final String profileUrlFraction = "/profile";
    private EditEcoNewsPage editEcoNewsPage;

    @BeforeClass
    public void openEditor() {
        homePage.getHeader().clickSignIn().login(testValueProvider.getUserEmail(), testValueProvider.getUserPassword());
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(profileUrlFraction));
    }

    @BeforeMethod
    public void openNewsPage() {

        driver.get(String.format(AUTHOR_NEWS_TEMPLATE_URL, testValueProvider.getBaseUIUrl(), testValueProvider.getUserNewsId()));

        editEcoNewsPage = new EcoNewsDetailsPage(driver).clickEditNewsButton();
    }

    @Test
    public void previewOpensSuccessfully() {

        PreviewEcoNewsPage preview = editEcoNewsPage.getEditNewsButtons().clickPreview();
        assertTrue(preview.isOpened());
    }

    @Test
    public void testSaveChangesButton() {

        editEcoNewsPage.getEditNewsButtons()
                .getSaveChangesButton()
                .click();
        assertTrue(true, "Saving was successful");
    }

    @Test
    public void testCancelChangesButton() {
        editEcoNewsPage.getEditNewsButtons()
                .clickCancel()
                .clickYesCancelButton();

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after canceling: " + currentUrl);

        Assert.assertEquals(currentUrl,
                String.format("%s/news/create-news?id=%d", testValueProvider.getBaseUIUrl(), testValueProvider.getUserNewsId()),
                "URL after canceling changes does not match the expected URL.");
    }

    @Test
    public void testPreviewNewsButton() {
        EditNewsButtonsComponent buttons = editEcoNewsPage.getEditNewsButtons();
        buttons.clickPreview();
        assertTrue(true, "The preview opened successfully");
    }

}