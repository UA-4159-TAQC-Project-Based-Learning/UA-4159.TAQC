package com.greencity.ui.components.header.actions;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.header.actions.search.SearchOverlay;
import com.greencity.ui.components.header.user.AuthPanel;
import com.greencity.ui.components.header.user.UserMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderControls extends BaseComponent {

    private static String authPanelCss = "[class=\"sign-in\"], [class=\\\"sign-up\\\"]\"";
    private static String loggedInUserMenuCss = ".header_user-wrp";
    private static String languageSwitcherCss = ".header_lang-switcher-wrp";
    private static String burgerButtonCss = ".header_burger-btn";
    private static String searchIconCss = ".search-icon";
    private static String searchOverlayRootCss = "app-search-popup";

    public HeaderControls(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean hasAuthPanel() {
        return !rootElement.findElements(By.cssSelector(authPanelCss)).isEmpty();
    }

    public boolean hasUserMenu() {
        return !rootElement.findElements(By.cssSelector(loggedInUserMenuCss)).isEmpty();
    }

    public AuthPanel authPanel() {
        return new AuthPanel(driver, rootElement);
    }

    public UserMenu userMenu() {
        WebElement userRoot = rootElement.findElement(By.cssSelector(loggedInUserMenuCss));
        return new UserMenu(driver, userRoot);
    }

    public LanguageSwitcher languageSwitcher() {
        WebElement langRoot = rootElement.findElement(By.cssSelector(languageSwitcherCss));
        return new LanguageSwitcher(driver, langRoot);
    }

    public SearchOverlay openSearch() {
        WebElement searchIcon = rootElement.findElement(By.cssSelector(searchIconCss));
        clickDynamicElement(searchIcon);

        WebElement overlayRoot = driver.findElement(By.cssSelector(searchOverlayRootCss));
        return new SearchOverlay(driver, overlayRoot);
    }

    public void clickSignIn() {
        if (hasAuthPanel()) {
            authPanel().clickSignIn();
        }
    }

    public void clickSignUp() {
        if (hasAuthPanel()) {
            authPanel().clickSignUp();
        }
    }

    public boolean hasBurgerIcon() {
        return !rootElement.findElements(By.cssSelector(burgerButtonCss)).isEmpty();
    }

    public void clickBurgerIcon() {
        if (hasBurgerIcon()) {
            rootElement.findElement(By.cssSelector(burgerButtonCss)).click();
        }
    }

}
