package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.CreateNewsPreviewPage;
import com.greencity.ui.pages.EcoNewsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewsButtonsComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'tertiary-global-button')]")
    private WebElement cancelButton;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'secondary-global-button')]")
    private WebElement previewButton;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'primary-global-button')]")
    private WebElement publishButton;


    public CreateNewsButtonsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Check if 'Cancel' button is visible")
    public boolean isCancelVisible() {
        waitUntilElementVisible(cancelButton); // from Base
        return cancelButton.isDisplayed();
    }

    @Step("Check if 'Cancel' button is enabled")
    public boolean isCancelEnabled() {
        waitUntilElementClickable(cancelButton); // from Base
        return cancelButton.isEnabled();
    }

    @Step("Check if 'Preview' button is visible")
    public boolean isPreviewVisible() {
        waitUntilElementVisible(previewButton);
        return previewButton.isDisplayed();
    }
    @Step("Check if 'Preview' button is enabled")
    public boolean isPreviewEnabled() {
        waitUntilElementClickable(previewButton);
        return previewButton.isEnabled();
    }

    @Step("Check if 'Publish' button is visible")
    public boolean isPublishVisible() {
        waitUntilElementVisible(publishButton);
        return publishButton.isDisplayed();
    }

    @Step("Check if 'Publish' button is enabled")
    public boolean isPublishEnabled() {
        waitUntilElementClickable(publishButton);
        return publishButton.isEnabled();
    }

    @Step("Get text of 'Cancel' button")
    public String getCancelButtonText() {
        return cancelButton.getText().trim();
    }

    @Step("Get text of 'Preview' button")
    public String getPreviewButtonText() {
        return previewButton.getText().trim();
    }

    @Step("Get text of 'Publish' button")
    public String getPublishButtonText() {
        return publishButton.getText().trim();
    }

    @Step("Click 'Preview' news button")
    public CreateNewsPreviewPage clickPreviewButton() {
        clickDynamicElement(previewButton);
        waitUntilPageLouder();
        return new CreateNewsPreviewPage(driver);
    }

    @Step("Click 'Publish' button")
    public EcoNewsPage clickPublish() {
        if (!isPublishEnabled()) {
            throw new IllegalArgumentException("Publish button is disabled. Check if all required fields are filled out");
        }
        publishButton.click();
        waitUntilPageLouder();
        return new EcoNewsPage(driver);
    }

    @Step("Click 'Cancel' news button")
    public CancelNewsModal clickCancelButton() {
        clickDynamicElement(cancelButton);
        waitUntilPageLouder();
        return new CancelNewsModal(driver);
    }

}
