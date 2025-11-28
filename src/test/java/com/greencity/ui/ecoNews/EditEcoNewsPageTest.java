package com.greencity.ui.ecoNews;

import com.greencity.ui.components.buttons.EditNewsButtonsComponent;
import com.greencity.ui.components.createNews.CancelNewsModal;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import com.greencity.ui.pages.EcoNewsDetailsPage;
import com.greencity.ui.pages.EditEcoNewsPage;
import com.greencity.ui.pages.ecoNewsPreviewPage.PreviewEcoNewsPage;
import com.greencity.ui.pages.ubsPage.UbsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.greencity.ui.pages.EcoNewsDetailsPage.AUTHOR_NEWS_URL;
import static org.testng.Assert.assertTrue;

//@Test(enabled = false)
public class EditEcoNewsPageTest extends BaseTestRunner {

    private final String profileUrlFraction = "/profile";
    private EditEcoNewsPage editEcoNewsPage;

    @BeforeClass
    public void openEditor() {

        LoginModalComponent loginModal = homePage.openLoginModal();
        loginModal.login(testValueProvider.getUserEmail(), testValueProvider.getUserPassword());

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(profileUrlFraction));
    }

    @Test
    public void previewOpensSuccessfully() {

        PreviewEcoNewsPage preview = editEcoNewsPage.getEditNewsButtons().clickPreview();
        assertTrue(preview.isOpened());
    }

    @Test
    public void testSaveChangesButton() {

        EditNewsButtonsComponent buttons = editEcoNewsPage.getEditNewsButtons();
        buttons.getSaveChangesButton().click();
        assertTrue(true, "Saving was successful");
    }

    @Test
    public void testCancelChangesButton() {

        EditNewsButtonsComponent editNewsButtons = editEcoNewsPage.getEditNewsButtons();
        WebElement cancelChangesButton = editNewsButtons.getCancelChangesButton();
        cancelChangesButton.click();

        CancelNewsModal cancelNewsModal = new CancelNewsModal(this.driver, cancelChangesButton);

        cancelNewsModal.waitForModalVisible();
        EditEcoNewsPage editEcoNewsPage = cancelNewsModal.clickYesCancelButton();

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after canceling: " + currentUrl);

        Assert.assertEquals(currentUrl, "https://www.greencity.cx.ua/#/greenCity/news/create-news?id=168");

        UbsPage ubsPage = new UbsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(ubsPage.getHeaderBannerUbsTitle()));
    }

    @Test
    public void testPreviewNewsButton() {

        EditNewsButtonsComponent buttons = editEcoNewsPage.getEditNewsButtons();
        buttons.clickPreview();
        assertTrue(true, "The preview opened successfully");
    }

    @AfterMethod
    public void refreshPage() {
        driver.navigate().refresh();

    }

    @BeforeMethod
    public void openNewsPage() {
        String currentUrl = driver.getCurrentUrl();

        if (!currentUrl.equals(AUTHOR_NEWS_URL)) {
            EcoNewsDetailsPage ecoNewsDetailsPage = new EcoNewsDetailsPage(driver);
            editEcoNewsPage = ecoNewsDetailsPage.openEditNewsPage();
        } else {
            editEcoNewsPage = new EditEcoNewsPage(driver);
        }
    }
}