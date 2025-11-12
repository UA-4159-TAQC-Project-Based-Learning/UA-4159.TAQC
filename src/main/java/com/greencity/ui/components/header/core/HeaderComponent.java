package com.greencity.ui.components.header.core;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderComponent extends BaseComponent {

    public enum NavItem { ECO_NEWS, EVENTS, PLACES, ABOUT_US, MY_SPACE_UBS_COURIER }
    // TODO -> move enums to data layer ?? ^^

    public enum Language {
        // TODO
    }

    @Getter
    @FindBy(xpath = ".//img[@src='assets/img/logo.svg']")
    private WebElement logo;

    @Getter
    @FindBy(css = ".header_navigation-menu-left")
    private WebElement leftNavRoot;

    @Getter
    @FindBy(css = ".header_navigation-menu-right")
    private WebElement rightNavRoot;

    // mobile view only
    @Getter
    @FindBy(css = ".header_burger-btn .menu-icon")
    private WebElement burgerIcon;

    // displayed only after burger click (mobile)
    @FindBy(css = ".header_navigation-menu-left-col")
    private WebElement mobileMenuRoot;

    // @Getter
    // private final NavBar navBar;

    // @Getter
    // private final HeaderControlsWrapper controls;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

}