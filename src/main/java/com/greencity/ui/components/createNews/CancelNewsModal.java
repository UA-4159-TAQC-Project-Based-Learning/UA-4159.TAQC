package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EditEcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    // Used to support overloaded constructor
    @Getter
    private static final By MODAL_ROOT_LOCATOR = By.cssSelector("app-warning-pop-up");

    public CancelNewsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    /**
     * Overloaded constructor: waits for the auth modal to appear
     * and uses it as the component root.
     */
    public CancelNewsModal(WebDriver driver) {
        this(driver, new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(MODAL_ROOT_LOCATOR)));
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
}
