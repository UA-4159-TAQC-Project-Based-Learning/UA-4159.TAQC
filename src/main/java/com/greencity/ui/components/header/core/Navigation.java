package com.greencity.ui.components.header.core;

import com.greencity.ui.components.BaseComponent;
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

}
