package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EditEcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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


    public void waitForModalVisible() {

//        wait.until(ExpectedConditions.visibilityOf(rootElement));
    }

    public CreateNewsPage clickContinueEditingButton() {
        waitForModalVisible();
        clickDynamicElement(continueNewsEditingModalButton);
        return new CreateNewsPage(driver);
    }

    public EditEcoNewsPage clickYesCancelButton() {
        waitForModalVisible();
        yesCancelChangesModalButton.click();
        return new EditEcoNewsPage(driver);
    }

    public CreateNewsPage clickCloseIcon() {
        waitForModalVisible();
        crossIconForCloseChangesModal.click();
        return new CreateNewsPage(driver);
    }

    public CancelNewsModal waitUntilOpened() {
        waitUntilElementVisible(cancelNewsChangesModalTitle);
        return new CancelNewsModal(driver, rootElement);
    }

    public void waitForModalClosed() {
        waitUntilElementInvisible(rootElement);
    }

    public boolean isVisible() {
        return rootElement.isDisplayed();
    }

    // Added alternative locators for the Cancel News modal buttons.
    // These specific versions are more stable in CancelNewsTest.
    @Getter
    @FindBy(css = "app-warning-pop-up button.primary-global-button")
    private WebElement yesCancelChangesModalButton_v2_cancelTestUsage;

    @Getter
    @FindBy(css = "app-warning-pop-up button.secondary-global-button")
    private WebElement continueNewsEditingModalButton_v2_cancelTestUsage;

    @Getter
    @FindBy(css = "app-warning-pop-up [class*='close']")
    private WebElement crossIconForCloseChangesModal_v2_cancelTestUsage;

    // BACKDROP_LOCATOR is outside pop-up root
    private static final By BACKDROP_LOCATOR = By.cssSelector(".cdk-overlay-backdrop");
    public void closeByClickingOutsidePopUp() {
        WebElement backdrop = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> d.findElement(BACKDROP_LOCATOR));

        clickDynamicElement(backdrop);
        waitForModalClosed();
    }
}
