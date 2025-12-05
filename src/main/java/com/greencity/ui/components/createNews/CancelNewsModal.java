package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EditEcoNewsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CancelNewsModal extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'warning-title')]")
    private WebElement cancelNewsChangesModalTitle;

    @Getter
    @FindBy(xpath = ".//button[text() = ' Continue editing ']")
    private WebElement continueNewsEditingModalButton;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'secondary-global-button')]")
    private WebElement yesCancelChangesModalButton;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'close')]")
    private WebElement crossIconForCloseChangesModal;


    public CancelNewsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    @Step("Wait until the Cancel Confirmation Dialog is visible")
    public void waitForModalVisible() {
       wait.until(ExpectedConditions.visibilityOf(rootElement));
    }

    @Step("Click 'Continue editing' button")
    public CreateNewsPage clickContinueEditingButton() {
        waitForModalVisible();
        clickDynamicElement(continueNewsEditingModalButton);
        return new CreateNewsPage(driver);
    }

    @Step("Click 'Yes, cancel' button")
    public EditEcoNewsPage clickYesCancelButton() {
        waitForModalVisible();
        yesCancelChangesModalButton.click();
        return new EditEcoNewsPage(driver);
    }

    @Step("Click cross icon to close Cancel Confirmation Dialog")
    public CreateNewsPage clickCloseIcon() {
        waitForModalVisible();
        crossIconForCloseChangesModal.click();
        return new CreateNewsPage(driver);
    }
}
