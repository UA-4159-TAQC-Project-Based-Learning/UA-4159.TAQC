package com.greencity.ui.components.header.actions;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.header.actions.search.SearchOverlay;
import com.greencity.ui.components.header.navbar.MobileNavMenu;
import com.greencity.ui.components.header.user.AuthPanel;
import com.greencity.ui.components.header.user.UserMenu;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
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

    public boolean hasAuthPanel() {
        try { return  signInButton.isDisplayed() && signUpButton.isDisplayed(); } catch (Exception ignored) { return false; }
    }

    public boolean hasUserMenu() {
        try { return loggedInUserButton.isDisplayed(); } catch (Exception ignored) { return false; }
    }

    public AuthPanel authPanelComponent() { return new AuthPanel(driver, rootElement); }

    public UserMenu userMenuComponent() {
        return new UserMenu(driver, loggedInUserButton);
    }

    public LanguageSwitcher languageSwitcher() {
        return new LanguageSwitcher(driver, languageSwitcher);
    }

    public SearchOverlay openSearch() {
        clickDynamicElement(searchIcon);
        return new SearchOverlay(driver, searchOverlayRoot);
    }

    public LoginModalComponent clickSignIn() {
        if (!hasAuthPanel()) {throw new IllegalStateException("Auth panel is not available in header.");}

        authPanelComponent().clickSignIn();
        return new LoginModalComponent(driver);
    }

    // TODO: change return type once SignUpModal is ready
    public void clickSignUp() {
        if (hasAuthPanel()) {
            authPanelComponent().clickSignUp();
        }
    }

    public boolean hasBurgerIcon() {
        try { return burgerButton.isDisplayed(); } catch (Exception ignored) { return false; }
    }

    public MobileNavMenu clickBurgerIcon() {
        if (hasBurgerIcon()) {
            clickDynamicElement(burgerButton);
            return new MobileNavMenu(driver, burgerButton);
        } else {
            throw new IllegalStateException("Cannot click Burger icon.");
        }
    }
}
