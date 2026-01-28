package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.EditEcoNewsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CancelNewsModal extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'warning-title')]")
    private WebElement cancelNewsChangesModalTitle;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'warning-subtitle')]")
    private WebElement cancelNewsChangesModalSubTitle;

    @Getter
    @FindBy(xpath = ".//button[text() = ' Continue editing ']")
    private WebElement continueNewsEditingModalButton;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'primary-global-button')]")
    private WebElement yesCancelChangesModalButton;

    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'close')]")
    private WebElement crossIconForCloseChangesModal;

    // Used to support overloaded constructor
    @Getter
    private static final By MODAL_ROOT_LOCATOR = By.cssSelector("app-warning-pop-up");

    public CancelNewsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    /**
     * Overloaded constructor: waits for the Cancel modal to appear
     * and uses it as the component root.
     */
    public CancelNewsModal(WebDriver driver) {
        this(driver, new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(MODAL_ROOT_LOCATOR)));
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
    public EcoNewsPage clickYesCancelButton() {
        waitForModalVisible();
        getYesCancelChangesModalButton().click();
        return new EcoNewsPage(driver);
    }

    @Step("Click cross icon to close Cancel Confirmation Dialog")
    public CreateNewsPage clickCloseIcon() {
        waitForModalVisible();
        crossIconForCloseChangesModal.click();
        return new CreateNewsPage(driver);
    }

    public CancelNewsModal waitUntilOpened() {
        waitUntilElementVisible(cancelNewsChangesModalTitle);
        return this;
    }

    public void waitForModalClosed() {
        waitUntilElementInvisible(rootElement);
    }

    @Step("Check if Cancel modal is visible")
    public boolean isVisible() {
        try {
            return rootElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Alternative locators for the Cancel News modal buttons.
    // These locators are more stable and are successfully working in the CancelNewsTest.
    @Getter
    @FindBy(css = "app-warning-pop-up button.primary-global-button")
    private WebElement yesCancelChangesModalButtonAlternative;

    @Getter
    @FindBy(css = "app-warning-pop-up button.secondary-global-button")
    private WebElement continueNewsEditingModalButtonAlternative;

    @Getter
    @FindBy(css = "app-warning-pop-up [class*='close']")
    private WebElement crossIconForCloseChangesModalAlternative;

    // BACKDROP_LOCATOR is outside pop-up root
    private static final By BACKDROP_LOCATOR = By.cssSelector(".cdk-overlay-backdrop");
    public void closeByClickingOutsidePopUp() {
        WebElement backdrop = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> d.findElement(BACKDROP_LOCATOR));

        clickDynamicElement(backdrop);
        waitForModalClosed();
    }

    @Step("Check if 'Continue editing' button is visible")
    public boolean isContinueEditingVisible() {
        return continueNewsEditingModalButton.isDisplayed();
    }

    @Step("Check if 'Yes, cancel' button is visible")
    public boolean isYesCancelVisible() {
        return yesCancelChangesModalButton.isDisplayed();
    }

    @Step("Check if close icon is visible")
    public boolean isCloseIconVisible() {
        waitUntilElementVisible(crossIconForCloseChangesModal);
        return crossIconForCloseChangesModal.isDisplayed();
    }

    @Step("Get Cancel modal title text")
    public String getModalTitleText() {
        return cancelNewsChangesModalTitle.getText().trim();
    }

    @Step("Get Cancel modal subtitle text")
    public String getModalSubtitleText() {
        return cancelNewsChangesModalSubTitle.getText().trim();
    }

    @Step("Check if 'Continue editing' button is enabled")
    public boolean isContinueEditingEnabled() {
        return continueNewsEditingModalButton.isEnabled();
    }

    @Step("Check if 'Yes, cancel' button is enabled")
    public boolean isYesCancelEnabled() {
        return yesCancelChangesModalButton.isEnabled();
    }

    @Step("Check if close icon is enabled")
    public boolean isCloseIconEnabled() {
        return crossIconForCloseChangesModal.isEnabled();
    }
}
