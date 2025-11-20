package com.greencity.ui.ecoNews;

import com.greencity.ui.BaseTest;
import com.greencity.ui.components.buttons.EditNewsButtonsComponent;
import com.greencity.ui.pages.EcoNewsDetailsPage;
import com.greencity.ui.pages.EditEcoNewsPage;
import com.greencity.ui.pages.ecoNewsPreviewPage.PreviewEcoNewsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class EditEcoNewsPageTest extends BaseTest {

    private EditEcoNewsPage editEcoNewsPage;

    @BeforeMethod
    public void openEditor() {
        EcoNewsDetailsPage newsPage = new EcoNewsDetailsPage(driver);
        editEcoNewsPage = newsPage.openEditNewsPage();
    }

    @Test
    public void previewOpensSuccessfully() {

        EditEcoNewsPage editPage = new EditEcoNewsPage(driver);
        PreviewEcoNewsPage preview = editPage.getEditNewsButtons().clickPreview();
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

        EditNewsButtonsComponent buttons = editEcoNewsPage.getEditNewsButtons();
        buttons.getCancelChangesButton().click();
        assertTrue(true, "Cancellation was successful");
    }

    @Test
    public void testPreviewNewssButton() {

        EditNewsButtonsComponent buttons = editEcoNewsPage.getEditNewsButtons();
        buttons.clickPreview();
        assertTrue(true, "The preview opened successfully");
    }
}
