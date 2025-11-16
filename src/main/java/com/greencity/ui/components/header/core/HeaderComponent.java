package com.greencity.ui.components.header.core;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.header.actions.HeaderControls;
import com.greencity.ui.components.header.actions.search.SearchOverlay;
import com.greencity.ui.components.header.navbar.NavBar;
import com.greencity.ui.utils.NavItem;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderComponent extends BaseComponent {

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

    @Getter
    private final NavBar navBar;

    @Getter
    private final HeaderControls controls;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        navBar = new NavBar(driver, leftNavRoot);
        controls = new HeaderControls(driver, rightNavRoot);
    }

    public void clickLogo() {
        clickDynamicElement(logo);
    }

    public boolean isLoggedIn() {
        return controls.hasUserMenu();
    }

    public String getUserFullName() {
        if (!controls.hasUserMenu()) {
            return "";
        }
        return controls.userMenu().getUserFirstAndLastNameAsText();
    }

    public void openNav(NavItem item) {
        navBar.open(item);
    }

    public void clickSignIn() {
        controls.clickSignIn();
    }

    public void clickSignUp() {
        controls.clickSignUp();
    }

    public void signOutIfLoggedIn() {
        if (controls.hasUserMenu()) {
            controls.userMenu().signOut();
        }
    }

}