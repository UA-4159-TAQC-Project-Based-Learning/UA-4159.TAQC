package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import com.greencity.ui.pages.profile.ProfilePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderBannerComponent extends BaseComponent {
    public static final By LOGIN_MODAL_ROOT_LOCATOR =
            By.xpath("//div[starts-with(@id,'cdk-overlay-')]");
    @Getter
    @FindBy(xpath = ".//h1")
    private WebElement headerBannerTitle;
    @Getter
    @FindBy(xpath = ".//p")
    private WebElement bannerDescription;
    @Getter
    @FindBy(xpath = ".//button[contains(text(),'Start forming a habit!')]")
    private WebElement startFormingHabitButton;
    @Getter
    @FindBy(xpath = ".//img[@id='guy-image']")
    private WebElement bannerImage;

    public HeaderBannerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Get header banner title text on the main page")
    public String getHeaderBannerTitleText() {
        waitUntilElementVisible(headerBannerTitle);
        return getHeaderBannerTitle().getText().trim();
    }

    @Step("Check if header banner title is displayed")
    public boolean isHeaderBannerTitleDisplayed() {
        return headerBannerTitle.isDisplayed();
    }

    @Step("Check if header banner title contains text: {expectedText}")
    public boolean isHeaderBannerTitleContainsText(String expectedText) {
        waitUntilElementVisible(headerBannerTitle);
        String actualText = headerBannerTitle.getText().trim();
        return actualText.contains(expectedText);
    }

    @Step("Get banner description text")
    public String getBannerDescriptionText() {
        return bannerDescription.getText().trim();
    }

    @Step("Check if banner description is displayed")
    public boolean isBannerDescriptionDisplayed() {
        return bannerDescription.isDisplayed();
    }

    @Step("Check if banner description contains text: {expectedText}")
    public boolean isBannerDescriptionContainsText(String expectedText) {
        waitUntilElementVisible(bannerDescription);
        String actualText = bannerDescription.getText().trim();
        return actualText.contains(expectedText);
    }

    @Step("Check if 'Start Forming Habit' button is displayed")
    public boolean isStartFormingHabitButtonDisplayed() {
        return startFormingHabitButton.isDisplayed();
    }

    @Step("Check if 'Start Forming Habit' button is enabled")
    public boolean isStartFormingHabitButtonEnabled() {
        return startFormingHabitButton.isEnabled();
    }

    @Step("Get 'Start Forming Habit' button text")
    public String getStartFormingHabitButtonText() {
        return startFormingHabitButton.getText().trim();
    }

    @Step("Check if banner image is displayed")
    public boolean isBannerImageDisplayed() {
        waitUntilElementVisible(bannerImage);
        return bannerImage.isDisplayed();
    }

    @Step("Get banner image URL")
    public String getBannerImageUrl() {
        return bannerImage.getAttribute("src");
    }

    @Step("Validate banner content: title, description, and button text")
    public boolean isBannerDisplayedCorrectly(String expectedTitle, String expectedDescriptionPart, String expectedButtonText) {
        return isHeaderBannerTitleDisplayed()
                && getHeaderBannerTitleText().equals(expectedTitle)
                && isBannerDescriptionContainsText(expectedDescriptionPart)
                && isStartFormingHabitButtonDisplayed()
                && getStartFormingHabitButtonText().equals(expectedButtonText);
    }

    @Step("Validate banner image URL matches expected")
    public boolean isBannerImageCorrect(String expectedUrl) {
        return isBannerImageDisplayed() && getBannerImageUrl().equals(expectedUrl);
    }

    @Step("Click 'Start Forming Habit' as logged-in user and open Profile page")
    public ProfilePage clickStartButtonForLoggedInUser() {
        clickDynamicElement(startFormingHabitButton);
        wait.until(ExpectedConditions.urlContains("/profile/"));
        return new ProfilePage(driver);
    }

    @Step("Click 'Start Forming Habit' as guest user and open Login modal")
    public LoginModalComponent clickStartButtonForGuestUser() {
        clickDynamicElement(startFormingHabitButton);
        WebElement loginModalRoot = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_MODAL_ROOT_LOCATOR));
        return new LoginModalComponent(driver, loginModalRoot);
    }
}
