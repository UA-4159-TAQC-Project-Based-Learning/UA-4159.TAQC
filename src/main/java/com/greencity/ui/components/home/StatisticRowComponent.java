package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.ubsPage.UbsPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StatisticRowComponent extends BaseComponent {
    //root $x("//div[@class='stat-row']")

    @FindBy(xpath = ".//div[@class='stat-row-image']//img")
    private WebElement ecoImage;

    @FindBy(xpath = ".//h3")
    private WebElement ecoTitle;

    @FindBy(xpath = ".//h3/span")
    private WebElement counterEcoItems;

    @FindBy(xpath = ".//p")
    private WebElement ecoDescription;

    @FindBy(xpath = ".//button[contains(@class, 'primary-global-button')]")
    private WebElement startHabitButton;

    @FindBy(xpath = ".//div[@class='location-row']//a")
    private WebElement locationLink;

    @FindBy(xpath = ".//div[@class='location-row']//img")
    private WebElement locationImage;


    public StatisticRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    //----------functional----------
    //----------ecoImage----------

    public String getImageSrc() {
        return ecoImage.getAttribute("src");
    }
    public boolean isImageDisplayed() {
        return ecoImage.isDisplayed();
    }
    public boolean isImageSrcCorrect(String expectedSrc) {
        return getImageSrc().equals(expectedSrc);
    }

    //----------ecoTitle----------

    public String getTitleText() {
        return ecoTitle.getText();
    }

    //----------counterEcoItems----------

    public int getCounterValue() {
        return Integer.parseInt(counterEcoItems.getText());
    }

    //----------ecoDescription----------

    public String getDescriptionText() {
        return ecoDescription.getText();
    }
    public boolean isDescriptionContainsText(String expectedText) {
        waitUntilElementVisible(ecoDescription);
        String actualText = ecoDescription.getText().trim();
        return actualText.contains(expectedText);
    }

    //----------startHabitButton----------

    public boolean isStartHabitButtonDisplayed() {
        return startHabitButton.isDisplayed();
    }

    public boolean isStartFormingHabitButtonEnabled() {
        return startHabitButton.isEnabled();
    }

    public String getStartFormingHabitButtonText() {
        return startHabitButton.getText().trim();
    }

    //----------locationLink----------
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

    //----------locationImage----------
    public String getLocationImageSrc() {
        return locationImage.getAttribute("src");
    }
    public boolean isLocationImageSrcCorrect(String expectedSrc) {
        return getLocationImageSrc().equals(expectedSrc);
    }

    //----------business logic----------

    public UbsPage clickStartHabitButtonForLoggedInUser() {
        startHabitButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/ubs"));
        return new UbsPage(driver);
    }
//    public LoginPopupComponent clickStartHabitButtonForGuestUser() {
//        clickDynamicElement(startHabitButton);
//        waitUntilElementVisible(loginPopup); // loginPopup (probably popup root)
//        return new LoginPopupComponent(driver, loginPopup);
//    }

}
