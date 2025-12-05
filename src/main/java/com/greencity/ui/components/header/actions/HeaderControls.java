package com.greencity.ui.components.header.actions;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.header.actions.search.SearchOverlay;
import com.greencity.ui.components.header.navbar.MobileNavMenu;
import com.greencity.ui.components.header.user.AuthPanel;
import com.greencity.ui.components.header.user.UserMenu;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeaderControls extends BaseComponent {

    // Needed for hasUserMenu() when DOM can be re-created
    @Getter
    private static final String USER_MENU_CSS = "#header_user-wrp";

    @Getter
    @FindBy(css = ".ubs-header-sing-in-img-greencity")
    private WebElement signInButton;

    @Getter
    @FindBy(css = ".header_sign-up-link")
    private WebElement signUpButton;

    @Getter
    @FindBy(css = USER_MENU_CSS)
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
        return signInButton.isDisplayed() && signUpButton.isDisplayed();
    }

    public boolean hasUserMenu() {
        // Intentionally not using @FindBy - header DOM may change, so the element should be re-located each time
        List<WebElement> elements = driver.findElements(By.cssSelector(USER_MENU_CSS));
        return !elements.isEmpty() && elements.get(0).isDisplayed();
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
        return burgerButton.isDisplayed();
    }

    public MobileNavMenu clickBurgerIcon() {
        clickDynamicElement(burgerButton);
        return new MobileNavMenu(driver, burgerButton);
    }
}
