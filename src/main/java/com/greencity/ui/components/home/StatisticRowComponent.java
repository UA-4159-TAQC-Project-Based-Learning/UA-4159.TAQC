package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import com.greencity.ui.pages.ubsPage.UbsPage;
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

    public String getImageSrc() {
        return ecoImage.getAttribute("src");
    }

    public boolean isImageDisplayed() {
        waitUntilElementVisible(ecoImage);
        return ecoImage.isDisplayed();
    }

    public boolean isImageSrcCorrect(String expectedSrc) {
        return getImageSrc().equals(expectedSrc);
    }


    public String getTitleText() {
        return ecoTitle.getText();
    }

    public int getCounterValue() {
        return Integer.parseInt(counterEcoItems.getText());
    }


    public String getDescriptionText() {
        return ecoDescription.getText();
    }

    public boolean isDescriptionContainsText(String expectedText) {
        waitUntilElementVisible(ecoDescription);
        String actualText = ecoDescription.getText().trim();
        return actualText.contains(expectedText);
    }


    public boolean isStartHabitButtonDisplayed() {
        return startHabitButton.isDisplayed();
    }

    public boolean isStartFormingHabitButtonEnabled() {
        return startHabitButton.isEnabled();
    }

    public String getStartFormingHabitButtonText() {
        return startHabitButton.getText().trim();
    }

    public boolean isLocationLinkDisplayed() {
        return locationLink.isDisplayed();
    }

    public boolean isLocationLinkClickable() {
        try {
            waitUntilElementClickable(locationLink);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isLocationLinkTextCorrect(String expectedText) {
        return locationLink.getText().trim().equals(expectedText);
    }

    public String getLocationImageSrc() {
        return locationImage.getAttribute("src");
    }

    public boolean isLocationImageSrcCorrect(String expectedSrc) {
        return getLocationImageSrc().equals(expectedSrc);
    }

    public UbsPage clickStartHabitButtonForLoggedInUser() {
        startHabitButton.click();
        wait.until(ExpectedConditions.urlContains("/ubs"));
        return new UbsPage(driver);
    }

    public LoginModalComponent clickStartHabitButtonForGuestUser() {
        clickDynamicElement(startHabitButton);
        WebElement loginModalRoot = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_MODAL_ROOT_LOCATOR));
        return new LoginModalComponent(driver, loginModalRoot);
    }

}
