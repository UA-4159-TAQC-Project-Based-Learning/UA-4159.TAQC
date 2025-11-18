package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import com.greencity.ui.pages.profile.ProfilePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;

public class HeaderBannerComponent extends BaseComponent {
    static final By LOGIN_MODAL_ROOT_LOCATOR =
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

    public String getHeaderBannerTitleText() {
        waitUntilElementVisible(headerBannerTitle);
        return getHeaderBannerTitle().getText().trim();
    }

    public boolean isHeaderBannerTitleDisplayed() {
        return headerBannerTitle.isDisplayed();
    }

    public boolean isHeaderBannerTitleContainsText(String expectedText) {
        waitUntilElementVisible(headerBannerTitle);
        String actualText = headerBannerTitle.getText().trim();
        return actualText.contains(expectedText);
    }

    public String getBannerDescriptionText() {
        return bannerDescription.getText().trim();
    }

    public boolean isBannerDescriptionDisplayed() {
        return bannerDescription.isDisplayed();
    }

    public boolean isBannerDescriptionContainsText(String expectedText) {
        waitUntilElementVisible(bannerDescription);
        String actualText = bannerDescription.getText().trim();
        return actualText.contains(expectedText);
    }

    public boolean isStartFormingHabitButtonDisplayed() {
        return startFormingHabitButton.isDisplayed();
    }

    public boolean isStartFormingHabitButtonEnabled() {
        return startFormingHabitButton.isEnabled();
    }

    public String getStartFormingHabitButtonText() {
        return startFormingHabitButton.getText().trim();
    }

    public boolean isBannerImageDisplayed() {
        waitUntilElementVisible(bannerImage);
        return bannerImage.isDisplayed();
    }

    public String getBannerImageUrl() {
        return bannerImage.getAttribute("src");
    }

    public boolean isBannerDisplayedCorrectly(String expectedTitle, String expectedDescriptionPart, String expectedButtonText) {
        return isHeaderBannerTitleDisplayed()
                && getHeaderBannerTitleText().equals(expectedTitle)
                && isBannerDescriptionContainsText(expectedDescriptionPart)
                && isStartFormingHabitButtonDisplayed()
                && getStartFormingHabitButtonText().equals(expectedButtonText);
    }

    public boolean isBannerImageCorrect(String expectedUrl) {
        return isBannerImageDisplayed() && getBannerImageUrl().equals(expectedUrl);
    }

    public ProfilePage clickStartButtonForLoggedInUser() {
        clickDynamicElement(startFormingHabitButton);
        wait.until(ExpectedConditions.urlContains("/profile/"));
        return new ProfilePage(driver);
    }

    public LoginModalComponent clickStartButtonForGuestUser() {
        clickDynamicElement(startFormingHabitButton);
        WebElement loginModalRoot = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_MODAL_ROOT_LOCATOR));
        return new LoginModalComponent(driver, loginModalRoot);
    }

}
