package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.CreateNewsPreviewPage;
import com.greencity.ui.pages.EcoNewsPage;
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

    public CreateNewsPreviewPage clickPreviewButton() {
        clickDynamicElement(previewButton);
        waitUntilPageLouder();
        return new CreateNewsPreviewPage(driver);
    }

    public boolean isPublishEnabled() {
        return publishButton.isEnabled();
    }

    public EcoNewsPage clickPublish() {
        if (!isPublishEnabled()) {
            throw new IllegalArgumentException("Publish button is disabled. Check if all required fields are filled out");
        }
        publishButton.click();
        waitUntilPageLouder();
        return new EcoNewsPage(driver);
    }

}
