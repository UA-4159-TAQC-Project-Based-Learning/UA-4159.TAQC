package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.profile.ProfilePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderBannerComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//h1")
    private WebElement headerBannerTitle;

    @FindBy(xpath = ".//p")
    private WebElement bannerDescription;

    @FindBy(xpath = ".//button[contains(text(),'Start forming a habit!')]")
    private WebElement startFormingHabitButton;

    @FindBy(xpath = ".//img[@id='guy-image']")
    private WebElement bannerImage;

    public HeaderBannerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    //--------- headerBannerTitle---------

    private WebElement getHeaderBanner() {
        return headerBannerTitle;
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

    //--------- bannerDescription---------
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

    //--------- startFormingHabitButton---------
    public boolean isStartFormingHabitButtonDisplayed() {
        return startFormingHabitButton.isDisplayed();
    }

    public boolean isStartFormingHabitButtonEnabled() {
        return startFormingHabitButton.isEnabled();
    }

    public String getStartFormingHabitButtonText() {
        return startFormingHabitButton.getText().trim();
    }

    //--------- bannerImage ---------
    public boolean isBannerImageDisplayed() {
        return bannerImage.isDisplayed();
    }

    public String getBannerImageUrl() {
        return bannerImage.getAttribute("src");
    }

    //----------functional----------
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

    //----------business----------
    //click on button when User is logged-in
    public ProfilePage clickStartButtonForLoggedInUser() {
        clickDynamicElement(startFormingHabitButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/profile/"));
        return new ProfilePage(driver);
    }

    //click on button when User is as a Guest
//    public LoginPopupComponent clickStartButtonForGuestUser() {
//        clickDynamicElement(startFormingHabitButton);
//        waitUntilElementVisible(loginPopup); // loginPopup (probably popup root)
//        return new LoginPopupComponent(driver, loginPopup);
//    }

}
