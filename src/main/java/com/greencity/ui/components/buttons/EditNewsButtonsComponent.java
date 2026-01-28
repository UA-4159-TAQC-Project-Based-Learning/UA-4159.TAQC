package com.greencity.ui.components.buttons;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.createNews.CancelNewsModal;
import com.greencity.ui.pages.ecoNewsPreviewPage.PreviewEcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditNewsButtonsComponent extends BaseComponent {

    @Getter
    @FindBy(css = "button.primary-global-button")
    private WebElement saveChangesButton;

    @Getter
    @FindBy(css = "button.tertiary-global-button")
    private WebElement cancelChangesButton;

    @Getter
    @FindBy(css = "button.secondary-global-button")
    private WebElement previewNewsButton;


    @FindBy(xpath = "//div[contains(@class, 'popup-dialog-container')]")
    WebElement cancelNewsModalRoot;

    public EditNewsButtonsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public PreviewEcoNewsPage clickPreview() {
        previewNewsButton.click();
        return new PreviewEcoNewsPage(driver);
    }
    public CancelNewsModal clickCancel() {
        cancelChangesButton.click();
        sleep(500); // Adding a brief sleep to allow modal to appear
        return new CancelNewsModal(driver, cancelNewsModalRoot);
    }
}
