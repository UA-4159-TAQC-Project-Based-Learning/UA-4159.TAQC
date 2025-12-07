package com.greencity.ui.components.header.core;

import com.greencity.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Navigation extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//a[contains(normalize-space(.), 'Eco news') or contains(normalize-space(.), 'Еко новини')]")
    private WebElement ecoNewsLink;

    @Getter
    @FindBy(xpath = ".//a[contains(normalize-space(.), 'Events') or contains(normalize-space(.), 'Події')]")
    private WebElement eventsLink;

    @Getter
    @FindBy(xpath = ".//a[contains(normalize-space(.), 'Places') or contains(normalize-space(.), 'Карта')]")
    private WebElement placesLink;

    @Getter
    @FindBy(xpath = ".//a[contains(normalize-space(.), 'About Us') or contains(normalize-space(.), 'Про нас')]")
    private WebElement aboutUsLink;

    @Getter
    @FindBy(xpath = ".//a[contains(normalize-space(.), 'My Spaces') or contains(normalize-space(.), 'Мій кабінет')]")
    private WebElement mySpacesLink;

    @Getter
    @FindBy(xpath = ".//a[contains(normalize-space(.), 'UBS Courier') or contains(normalize-space(.), 'UBS кур'єр')]")
    private WebElement ubsCourierLink;


    public Navigation(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    // ---------- CLICK METHODS ----------

    @Step("Click on Eco News link")
    public void clickEcoNews() {
        ecoNewsLink.click();
    }

    @Step("Click on Events link")
    public void clickEvents() {
        eventsLink.click();
    }

    @Step("Click on Places link")
    public void clickPlaces() {
        placesLink.click();
    }

    @Step("Click on About Us link")
    public void clickAboutUs() {
        aboutUsLink.click();
    }

    @Step("Click on My Spaces link")
    public void clickMySpaces() {
        mySpacesLink.click();
    }

    @Step("Click on UBS Courier link")
    public void clickUbsCourier() {
        ubsCourierLink.click();
    }
}
