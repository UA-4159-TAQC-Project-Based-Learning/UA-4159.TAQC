package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CancelNewsModal extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'warning-title')]")
    private WebElement cancelNewsModalTitle;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'secondary-global-button')]")
    private WebElement continueEditingModalButton;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'primary-global-button')]")
    private WebElement yesCancelModalButton;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'close')]")
    private WebElement crossIconNewsModal;

    public CancelNewsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void waitForModalVisible() {
        wait.until(ExpectedConditions.visibilityOf(rootElement));
    }

    public CreateNewsPage clickContinueEditingButton() {
        waitForModalVisible();
        clickDynamicElement(continueEditingModalButton);
        return new CreateNewsPage(driver);
    }

    public EcoNewsPage clickYesCancelButton() {
        waitForModalVisible();
        yesCancelModalButton.click();
        return new EcoNewsPage(driver);
    }

    public CreateNewsPage clickCloseIcon() {
        waitForModalVisible();
        crossIconNewsModal.click();
        return new CreateNewsPage(driver);
    }

}
