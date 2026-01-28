package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import com.greencity.ui.pages.ubsPage.UbsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.greencity.ui.components.home.HeaderBannerComponent.LOGIN_MODAL_ROOT_LOCATOR;

public class StatisticRowComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//div[@class='stat-row-image']//img")
    private WebElement ecoImage;
    @Getter
    @FindBy(xpath = ".//h3")
    private WebElement ecoTitle;
    @Getter
    @FindBy(xpath = ".//h3/span")
    private WebElement counterEcoItems;
    @Getter
    @FindBy(xpath = ".//p")
    private WebElement ecoDescription;
    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'primary-global-button')]")
    private WebElement startHabitButton;
    @Getter
    @FindBy(xpath = ".//div[@class='location-row']//a")
    private WebElement locationLink;
    @Getter
    @FindBy(xpath = ".//div[@class='location-row']//img")
    private WebElement locationImage;


    public StatisticRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Get eco image 'src' attribute")
    public String getImageSrc() {
        return ecoImage.getAttribute("src");
    }

    @Step("Check if eco image is displayed")
    public boolean isImageDisplayed() {
        waitUntilElementVisible(ecoImage);
        return ecoImage.isDisplayed();
    }

    @Step("Check if eco image source is correct: {expectedSrc}")
    public boolean isImageSrcCorrect(String expectedSrc) {
        return getImageSrc().equals(expectedSrc);
    }

    @Step("Get eco section title text")
    public String getTitleText() {
        return ecoTitle.getText();
    }

    @Step("Get eco items counter value")
    public int getCounterValue() {
        return Integer.parseInt(counterEcoItems.getText());
    }

    @Step("Get eco section description text")
    public String getDescriptionText() {
        return ecoDescription.getText();
    }

    @Step("Check if eco description contains text: {expectedText}")
    public boolean isDescriptionContainsText(String expectedText) {
        waitUntilElementVisible(ecoDescription);
        String actualText = ecoDescription.getText().trim();
        return actualText.contains(expectedText);
    }

    @Step("Check if 'Start Habit' button is displayed")
    public boolean isStartHabitButtonDisplayed() {
        return startHabitButton.isDisplayed();
    }

    @Step("Check if 'Start Habit' button is enabled")
    public boolean isStartFormingHabitButtonEnabled() {
        return startHabitButton.isEnabled();
    }

    @Step("Get 'Start Habit' button text")
    public String getStartFormingHabitButtonText() {
        return startHabitButton.getText().trim();
    }

    @Step("Check if location link is displayed")
    public boolean isLocationLinkDisplayed() {
        return locationLink.isDisplayed();
    }

    @Step("Check if location link is clickable")
    public boolean isLocationLinkClickable() {
        try {
            waitUntilElementClickable(locationLink);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Check if location link text is correct: {expectedText}")
    public boolean isLocationLinkTextCorrect(String expectedText) {
        return locationLink.getText().trim().equals(expectedText);
    }

    @Step("Get location image 'src' attribute")
    public String getLocationImageSrc() {
        return locationImage.getAttribute("src");
    }

    @Step("Check if location image source is correct: {expectedSrc}")
    public boolean isLocationImageSrcCorrect(String expectedSrc) {
        return getLocationImageSrc().equals(expectedSrc);
    }

    @Step("Click 'Start Habit' as logged-in user and open UBS page")
    public UbsPage clickStartHabitButtonForLoggedInUser() {
        startHabitButton.click();
        wait.until(ExpectedConditions.urlContains("/ubs"));
        return new UbsPage(driver);
    }

    @Step("Click 'Start Habit' as guest user and open Login modal")
    public LoginModalComponent clickStartHabitButtonForGuestUser() {
        clickDynamicElement(startHabitButton);
        WebElement loginModalRoot = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_MODAL_ROOT_LOCATOR));
        return new LoginModalComponent(driver, loginModalRoot);
    }

}
