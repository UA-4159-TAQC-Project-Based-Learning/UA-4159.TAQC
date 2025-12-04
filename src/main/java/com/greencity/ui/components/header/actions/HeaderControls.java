package com.greencity.ui.components.header.actions;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.header.actions.search.SearchOverlay;
import com.greencity.ui.components.header.navbar.MobileNavMenu;
import com.greencity.ui.components.header.user.AuthPanel;
import com.greencity.ui.components.header.user.UserMenu;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderControls extends BaseComponent {

    @Getter
    @FindBy(css = ".ubs-header-sing-in-img-greencity")
    private WebElement signInButton;

    @Getter
    @FindBy(css = ".header_sign-up-link")
    private WebElement signUpButton;

    @Getter
    @FindBy(css = "#header_user-wrp")
    private WebElement loggedInUserButton;

    @Getter
    @FindBy(css = ".header_lang-switcher-wrp")
    private WebElement languageSwitcher;

    @Getter
    @FindBy(css = ".header_burger-btn")
    private WebElement burgerButton;

    @Getter
    @FindBy(css = ".search-icon")
    private WebElement searchIcon;

    @Getter
    @FindBy(css = "app-search-popup")
    private WebElement searchOverlayRoot;

    public HeaderControls(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Check if auth panel (for logged-out user) is visible in the header")
    public boolean hasAuthPanel() {
        return signInButton.isDisplayed() && signUpButton.isDisplayed();
    }

    @Step("Check if user menu (for logged-in user) is visible in the header")
    public boolean hasUserMenu() {
        return loggedInUserButton.isDisplayed();
    }

    public AuthPanel authPanelComponent() { return new AuthPanel(driver, rootElement); }

    public UserMenu userMenuComponent() {
        return new UserMenu(driver, loggedInUserButton);
    }

    public LanguageSwitcher languageSwitcher() {
        return new LanguageSwitcher(driver, languageSwitcher);
    }

    @Step("Open search overlay")
    public SearchOverlay openSearch() {
        clickDynamicElement(searchIcon);
        return new SearchOverlay(driver, searchOverlayRoot);
    }

    @Step("Click 'Sign In' button in the header")
    public LoginModalComponent clickSignIn() {
        authPanelComponent().clickSignIn();
        return new LoginModalComponent(driver);
    }

    // TODO: change return type once SignUpModal is ready
    @Step("Click 'Sign Up' button in the header")
    public void clickSignUp() {
        if (hasAuthPanel()) {
            authPanelComponent().clickSignUp();
        }
    }

    @Step("Check if burger icon is visible in the header")
    public boolean hasBurgerIcon() {
        return burgerButton.isDisplayed();
    }

    @Step("Click burger icon to open mobile navigation")
    public MobileNavMenu clickBurgerIcon() {
        clickDynamicElement(burgerButton);
        return new MobileNavMenu(driver, burgerButton);
    }
}
